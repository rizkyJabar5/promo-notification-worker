package id.co.bni.surrounding.promo.workernotification.additional.entity.promo;

import id.co.bni.surrounding.promo.workernotification.additional.entity.BaseEntity;
import id.co.bni.surrounding.promo.workernotification.additional.entity.enums.Gender;
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
@Table(name = "MASTER_PROMO_1")
@ToString(onlyExplicitlyIncluded = true)
public class MasterPromo extends BaseEntity {
    @Id
    @Column(nullable = false, length = 21, updatable = false)
    private String id;

    @Column(name = "PROMO_ID")
    private String promoId;

    @Column(name = "BRAND")
    private String brand;

    private String category;

    private String religion;

    private Gender gender;

    private String segmentation;

    @Column(name = "PROMO_LOCATION")
    private String promoLocation;

    @Column(name = "TOTAL_ASSETS")
    private String totalAsset;

    @Column(name = "ACCOUNT_AGE")
    private Integer accountAge;

    @Column(name = "TRANSACTION_TIME")
    private ZonedDateTime transactionTime;

    @Column(name = "START_DATE")
    private ZonedDateTime startDate;

    @Column(name = "END_DATE")
    private ZonedDateTime endDate;

    private String type;
}
