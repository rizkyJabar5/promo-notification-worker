package id.co.bni.maverick.promoworkernotification.additional.entity.promo;

import id.co.bni.maverick.promoworkernotification.additional.entity.BaseEntity;
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
@Table(name = "master_merchant")
@ToString(onlyExplicitlyIncluded = true)
public class MasterMerchant extends BaseEntity {
    @Id
    @Column(nullable = false, length = 21, updatable = false)
    private String id;

    @Column(name = "MCC_GROUP")
    private String mccGroup;

    @Column(name = "MERCHANT_CATEGORY")
    private String merchantCategory;
}
