package id.co.bni.surrounding.promo.workernotification.service;

import id.co.bni.surrounding.promo.workernotification.additional.repository.promo.MasterPromoRepository;
import id.co.bni.surrounding.promo.workernotification.config.properties.KafkaProperties;
import id.co.bni.surrounding.promo.workernotification.model.BaseResponse;
import id.co.bni.surrounding.promo.workernotification.model.PromoPayload;
import id.co.bni.surrounding.promo.workernotification.event.PromoProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Log4j2
public class PromoService {
    private final KafkaProperties properties;
    private final PromoProducer producer;
    private final MasterPromoRepository masterPromoRepository;

    public BaseResponse broadcastPromo(String promoId) {
        var availablePromos = masterPromoRepository.findExactPromoUser(promoId);

        if (availablePromos.isEmpty()) {
            return BaseResponse.builder()
                    .status("WARNING")
                    .instance(String.valueOf(LocalDate.now()))
                    .message("Promo not available")
                    .build();
        }

        availablePromos
                .forEach(co -> {
                    var payload = PromoPayload.builder()
                            .userId(co.getUserId())
                            .promoId(co.getPromoId())
                            .type(co.getType())
                            .build();

                    producer.publishAsync(properties.topic(), payload);
                    log.info("Promo successfully to send to user {}", co.getUserId());
                });
        log.info("Broadcast promo {} are successfully", availablePromos.size());

        return BaseResponse.builder()
                .status("SUCCESS")
                .instance(String.valueOf(LocalDate.now()))
                .message("Success to publish")
                .build();
    }
}
