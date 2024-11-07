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

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MASTER_PROMO")
@ToString(onlyExplicitlyIncluded = true)
public class MasterPromo extends BaseEntity {
    @Id
    @Column(nullable = false, length = 21, updatable = false)
    private String id;

    @Column(name = "promo_id")
    private String promoId;

    private String brand;

    @Column(name = "category_dma")
    private Integer categoryDMA;

    @Column(name = "category_bo")
    private Integer categoryBO;

    private Integer religion;

    private Integer gender;

    private Integer segmentation;

    @Column(name = "PROMO_LOCATION")
    private Integer promoLocation;

    private Integer aum;

    @Column(name = "ACCOUNT_AGE")
    private Integer accountAge;

    @Column(name = "TRANSACTION_TIME")
    private Integer transactionTime;

    @Column(name = "START_DATE")
    private ZonedDateTime startDate;

    @Column(name = "END_DATE")
    private ZonedDateTime endDate;

    private String type;

    @Column(name = "is_priority")
    private Boolean isPriority;

    @Column(name = "is_other")
    private Boolean isOther;

    @Column(name = "is_coming_soon")
    private Boolean isComingsSoon;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
