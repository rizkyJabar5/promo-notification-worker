package id.co.bni.maverick.promoworkernotification.service;

import id.co.bni.maverick.promoworkernotification.additional.repository.promo.MasterMerchantRepository;
import id.co.bni.maverick.promoworkernotification.additional.repository.promo.MasterUserRepository;
import id.co.bni.maverick.promoworkernotification.additional.repository.promo.UserDmaRepository;
import id.co.bni.maverick.promoworkernotification.config.properties.KafkaProperties;
import id.co.bni.maverick.promoworkernotification.model.BaseResponse;
import id.co.bni.maverick.promoworkernotification.model.PromoPayload;
import id.co.bni.maverick.promoworkernotification.producer.PromoProducer;
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
    private final UserDmaRepository userDmaRepository;
    private final MasterUserRepository masterUserRepository;
    private final MasterMerchantRepository masterMerchantRepository;

    public BaseResponse sendPromo() {

        var payload = PromoPayload.builder()
                .userId("test")
                .promoId("1")
                .mccGroup("Toko")
                .merchantCategory("Makanan")
                .ranking(1)
                .build();

        producer.publishAsync(properties.topic(), payload);

        return BaseResponse.builder()
                .status("SUCCESS")
                .instance(String.valueOf(LocalDate.now()))
                .message("Success to publish")
                .build();
    }
}
