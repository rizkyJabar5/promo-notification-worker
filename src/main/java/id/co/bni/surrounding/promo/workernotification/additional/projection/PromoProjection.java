package id.co.bni.surrounding.promo.workernotification.additional.projection;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(UserPromo.class)
public class PromoProjection {
    @Id
    private String userId;

    @Id
    private String promoId;

    private String type;
}
