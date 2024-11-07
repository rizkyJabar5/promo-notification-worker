package id.co.bni.surrounding.promo.workernotification.service;

import id.co.bni.surrounding.promo.workernotification.additional.entity.promo.MasterPromo;
import id.co.bni.surrounding.promo.workernotification.additional.entity.promo.UserProperties;
import id.co.bni.surrounding.promo.workernotification.additional.repository.promo.MasterPromoRepository;
import id.co.bni.surrounding.promo.workernotification.additional.repository.promo.UserPropertiesRepository;
import id.co.bni.surrounding.promo.workernotification.config.properties.KafkaProperties;
import id.co.bni.surrounding.promo.workernotification.model.BaseResponse;
import id.co.bni.surrounding.promo.workernotification.model.PromoPayload;
import id.co.bni.surrounding.promo.workernotification.event.PromoProducer;
import id.co.bni.surrounding.promo.workernotification.util.PropertyOfUserEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static id.co.bni.surrounding.promo.workernotification.util.PropertyOfUserEnum.ACCOUNT_AGE;
import static id.co.bni.surrounding.promo.workernotification.util.PropertyOfUserEnum.AUM;
import static id.co.bni.surrounding.promo.workernotification.util.PropertyOfUserEnum.GENDER;
import static id.co.bni.surrounding.promo.workernotification.util.PropertyOfUserEnum.RELIGION;
import static id.co.bni.surrounding.promo.workernotification.util.PropertyOfUserEnum.SEGMENTATION;
import static id.co.bni.surrounding.promo.workernotification.util.PropertyOfUserEnum.TRANSACTION_TIME;

@Service
@RequiredArgsConstructor
@Log4j2
public class PromoService {
    public static final int ALL_PROPERTIES = 1;

    private final KafkaProperties properties;
    private final PromoProducer producer;
    private final MasterPromoRepository masterPromoRepository;
    private final UserPropertiesRepository userPropertiesRepository;

    public BaseResponse broadcastPromo(String promoId) {
        var availablePromo = this.masterPromoRepository.findPromoById(promoId)
                .orElseThrow(() -> new RuntimeException("Promo [" + promoId + "] not found"));

        var userProperties = this.storePromoUserProperties(availablePromo);
        var isNonFilteredByAllUserProperties = userProperties.values().stream().distinct().count() == ALL_PROPERTIES;

        if (isNonFilteredByAllUserProperties) {
            var promoAvailableForAllUser = userPropertiesRepository.findExactPromoUserNonFilterByProperties(availablePromo.getBrand());
            return publishFilteringPayload(promoAvailableForAllUser, availablePromo);
        }

        var eliminated = eliminateUserPropertiesGreaterThanOne(userProperties);

        if (eliminated.size() == 6) {
            var userMatchPromoWithSpecificProperty = userPropertiesRepository.findExactPromoUser(availablePromo);
            return publishFilteringPayload(userMatchPromoWithSpecificProperty, availablePromo);
        }

        var availablePromosWithFilteringAtomic = new AtomicReference<List<UserProperties>>();
        eliminated.forEach((key, value) -> {
            var promoAvailableForAllUser = userPropertiesRepository.findExactPromoUserNonFilterByProperties(availablePromo.getBrand());

            availablePromosWithFilteringAtomic.set(
                    promoAvailableForAllUser.stream()
                            .filter(user -> filterByProperty(user, PropertyOfUserEnum.valueOf(key), value))
                            .toList()
            );
        });

        var availablePromosWithFiltering = availablePromosWithFilteringAtomic.get();
        return this.publishFilteringPayload(availablePromosWithFiltering, availablePromo);
    }

    private boolean filterByProperty(UserProperties user, PropertyOfUserEnum property, Integer value) {
        return switch (property) {
            case SEGMENTATION -> user.getSegmentation().equals(value);
            case RELIGION -> user.getReligion().equals(value);
            case GENDER -> user.getGender().equals(value);
            case AUM -> user.getAum().equals(value);
            case ACCOUNT_AGE -> user.getAccountAge().equals(value);
            case TRANSACTION_TIME -> user.getTransactionTime().equals(value);
        };
    }

    private BaseResponse publishFilteringPayload(List<UserProperties> resultPromo, MasterPromo availablePromo) {
        var listOfUserId = resultPromo.stream()
                .map(UserProperties::getUserId)
                .toList();

        int userAvailableReceivedPromoCount = listOfUserId.size();

        if (listOfUserId.isEmpty()) {
            return BaseResponse.builder()
                    .status("WARNING")
                    .instance(String.valueOf(LocalDate.now()))
                    .message("Not available promo for users")
                    .build();
        }

        listOfUserId.forEach(userId -> {
            var payload = PromoPayload.builder()
                    .userId(userId)
                    .promoId(availablePromo.getPromoId())
                    .build();

            producer.publishAsync(this.properties.topic(), payload);
        });
        log.info("Broadcast promo {} are successfully", userAvailableReceivedPromoCount);

        return BaseResponse.builder()
                .status("SUCCESS")
                .instance(String.valueOf(LocalDate.now()))
                .message("Succeed to share Promo " + availablePromo.getBrand() + " to " + userAvailableReceivedPromoCount + " users")
                .build();
    }

    private Map<String, Integer> eliminateUserPropertiesGreaterThanOne(Map<String, Integer> userProperties) {
        return Map.copyOf(userProperties.entrySet()
                .stream()
                .filter(u -> u.getValue() != ALL_PROPERTIES)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    private Map<String, Integer> storePromoUserProperties(MasterPromo availablePromo) {
        var props = new HashMap<String, Integer>();
        props.put(GENDER.name(), availablePromo.getGender());
        props.put(RELIGION.name(), availablePromo.getReligion());
        props.put(SEGMENTATION.name(), availablePromo.getSegmentation());
        props.put(AUM.name(), availablePromo.getAum());
        props.put(ACCOUNT_AGE.name(), availablePromo.getAccountAge());
        props.put(TRANSACTION_TIME.name(), availablePromo.getTransactionTime());

        return props;
    }
}

