package id.co.bni.maverick.promoworkernotification.model;

import lombok.Builder;

@Builder
public record Temp(
        String userId,
        String promoId,
        Integer rank) implements BaseModel {
}
