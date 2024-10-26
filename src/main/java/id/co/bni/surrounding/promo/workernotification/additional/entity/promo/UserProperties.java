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

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_properties")
@ToString(onlyExplicitlyIncluded = true)
public class UserProperties extends BaseEntity {
    @Id
    @Column(nullable = false, length = 21, updatable = false)
    private String id;

    @Column(name = "USER_ID")
    private String userId;

    private Gender gender;

    private String religion;

    @Column(name = "ACCOUNT_AGE")
    private Integer accountAge;

    private Integer segmentation;

    @Column(name = "total_asset")
    private BigDecimal totalAsset;

    @Column(name = "TRANSACTION_TIME")
    private ZonedDateTime transactionTime;
}
