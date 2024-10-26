package id.co.bni.surrounding.promo.workernotification.model;

import lombok.Builder;

@Builder
public record PromoPayload(
        String userId,
        String promoId,
        String type) implements BasePayload {
}
