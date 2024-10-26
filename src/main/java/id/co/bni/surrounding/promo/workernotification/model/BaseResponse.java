package id.co.bni.surrounding.promo.workernotification.model;

import lombok.Builder;

@Builder
public record BaseResponse(
        String message,
        String instance,
        String status) {
}
