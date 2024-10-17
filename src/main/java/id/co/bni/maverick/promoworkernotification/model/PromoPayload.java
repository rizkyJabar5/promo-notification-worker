package id.co.bni.maverick.promoworkernotification.model;

import lombok.Builder;

@Builder
public record PromoPayload(
        String userId,
        String promoId,
        String mccGroup,
        String merchantCategory,
        Integer ranking) implements BasePayload {
}
