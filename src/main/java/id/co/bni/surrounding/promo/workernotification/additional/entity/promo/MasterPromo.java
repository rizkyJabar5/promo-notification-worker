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

    @Column(name = "PROMO_ID")
    private String promoId;

    @Column(name = "BRAND")
    private String brand;

    private Integer category;

    private Integer religion;

    private Integer gender;

    private Integer segmentation;

    @Column(name = "PROMO_LOCATION")
    private Integer promoLocation;

    @Column(name = "TOTAL_ASSETS")
    private Integer totalAsset;

    @Column(name = "ACCOUNT_AGE")
    private Integer accountAge;

    @Column(name = "TRANSACTION_TIME")
    private Integer transactionTime;

    @Column(name = "START_DATE")
    private ZonedDateTime startDate;

    @Column(name = "END_DATE")
    private ZonedDateTime endDate;

    private String type;
}
