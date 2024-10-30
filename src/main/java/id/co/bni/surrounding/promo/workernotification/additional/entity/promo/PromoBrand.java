package id.co.bni.surrounding.promo.workernotification.additional.entity.promo;

import id.co.bni.surrounding.promo.workernotification.additional.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "promo_branding")
@ToString(onlyExplicitlyIncluded = true)
public class PromoBrand extends BaseEntity {
    @Id
    @Column(nullable = false, length = 21, updatable = false)
    private String id;

    @Column(name = "USER_ID")
    private String userId;

    private String brand;

    private Integer category;

    private Integer score;
}
