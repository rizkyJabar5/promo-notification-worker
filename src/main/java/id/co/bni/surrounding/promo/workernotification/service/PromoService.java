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
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static id.co.bni.surrounding.promo.workernotification.util.PropertyOfUserEnum.GENDER;

@Service
@RequiredArgsConstructor
@Log4j2
public class PromoService {
    private final KafkaProperties properties;
    private final PromoProducer producer;
    private final MasterPromoRepository masterPromoRepository;
    private final UserPropertiesRepository userPropertiesRepository;

    public BaseResponse broadcastPromo(String promoId) {
        var availablePromo = this.masterPromoRepository.findPromoById(promoId);

        var userProperties = this.storePromoUserProperties(availablePromo);
        var hasSingleUserProperties = userProperties.values().stream().distinct().count() == 1;

        if (hasSingleUserProperties) {
            var promoAvailableForAllUser = userPropertiesRepository.findExactPromoUserNonFilterByProperties(availablePromo);
            return publishFilteringPayload(promoAvailableForAllUser, availablePromo);
        }

        var eliminated = eliminateUserPropertiesGreaterThanOne(userProperties);

        if (eliminated.size() == 6) {
            var userMatchPromoWithSpecificProperty = userPropertiesRepository.findExactPromoUser(availablePromo);
            return publishFilteringPayload(userMatchPromoWithSpecificProperty, availablePromo);
        }

        var availablePromosWithFilteringAtomic = new AtomicReference<List<UserProperties>>();
        eliminated.forEach((key, value) -> {
            var promoAvailableForAllUser = userPropertiesRepository.findExactPromoUserNonFilterByProperties(availablePromo);

            availablePromosWithFilteringAtomic.set(
                    promoAvailableForAllUser.stream()
                            .filter(user -> filterByProperty(user, PropertyOfUserEnum.valueOf(key), value))
                            .toList()
            );
        });

//        if (availablePromos.isEmpty()) {
//            return BaseResponse.builder()
//                    .status("WARNING")
//                    .instance(String.valueOf(LocalDate.now()))
//                    .message("Promo not found")
//                    .build();
//        }

        var availablePromosWithFiltering = availablePromosWithFilteringAtomic.get();
        return this.publishFilteringPayload(availablePromosWithFiltering, availablePromo);
    }

    private boolean filterByProperty(UserProperties user, PropertyOfUserEnum property, Integer value) {
        return switch (property) {
            case SEGMENTATION -> user.getSegmentation().equals(value);
            case RELIGION -> user.getReligion().equals(value);
            case GENDER -> user.getGender().equals(value);
            case TOTAL_ASSET -> user.getTotalAsset().equals(value);
            case ACCOUNT_AGE -> user.getAccountAge().equals(value);
            case TRANSACTION_TIME -> user.getTransactionTime().equals(value);
        };
    }

    private BaseResponse publishFilteringPayload(List<UserProperties> resultPromo, MasterPromo availablePromo) {
        // Non Filtering by user properties
        var listOfUserId = resultPromo.stream()
                .map(UserProperties::getUserId)
                .toList();

        listOfUserId.forEach(userId -> {
                    var payload = PromoPayload.builder()
                            .userId(userId)
                            .promoId(availablePromo.getPromoId())
                            .type(availablePromo.getType())
                            .build();

                    producer.publishAsync(this.properties.topic(), payload);
                    log.info("Promo successfully to send to user {}", userId);
                });
        log.info("Broadcast promo {} are successfully", listOfUserId.size());

        return BaseResponse.builder()
                .status("SUCCESS")
                .instance(String.valueOf(LocalDate.now()))
                .message("Success to publish")
                .build();
    }

    private Map<String, Integer> eliminateUserPropertiesGreaterThanOne(Map<String, Integer> userProperties) {
        return Map.copyOf(userProperties.entrySet()
                .stream()
                .filter(u -> u.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    private Map<String, Integer> storePromoUserProperties(MasterPromo availablePromo) {
        return Map.of(
                GENDER.name(), availablePromo.getGender(),
                "RELIGION", availablePromo.getReligion(),
                "SEGMENTATION", availablePromo.getSegmentation(),
                "TOTAL_ASSET", availablePromo.getTotalAsset(),
                "ACCOUNT_AGE", availablePromo.getAccountAge(),
                "TRANSACTION_TIME", availablePromo.getTransactionTime()
        );
    }
}

