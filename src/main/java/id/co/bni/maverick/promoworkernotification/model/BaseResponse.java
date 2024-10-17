package id.co.bni.maverick.promoworkernotification.model;

import lombok.Builder;

@Builder
public record BaseResponse(
        String message,
        String instance,
        String status) {
}
