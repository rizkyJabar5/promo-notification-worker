package id.co.bni.surrounding.promo.workernotification.additional.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {
    MALE('M'),
    FEMALE('F');

    private final Character value;
}
