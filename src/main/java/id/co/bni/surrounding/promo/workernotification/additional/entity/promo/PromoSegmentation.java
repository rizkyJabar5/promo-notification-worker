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
@Table(name = "promo_segmentation")
@ToString(onlyExplicitlyIncluded = true)
public class PromoSegmentation extends BaseEntity {
    @Id
    @Column(nullable = false, length = 21, updatable = false)
    private String id;

    private Integer segmentation;

    @Column(name = "category_dma")
    private Integer categoryDMA;

    @Column(name = "category_bo")
    private Integer categoryBO;

    private Integer score;
}
