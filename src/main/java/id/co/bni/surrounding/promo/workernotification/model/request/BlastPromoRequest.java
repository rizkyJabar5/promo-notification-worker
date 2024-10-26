package id.co.bni.surrounding.promo.workernotification.model.request;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record BlastPromoRequest(
        String promoIds) implements Serializable {
}
