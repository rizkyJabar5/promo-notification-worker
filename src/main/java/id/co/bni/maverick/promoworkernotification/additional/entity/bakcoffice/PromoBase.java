package id.co.bni.maverick.promoworkernotification.additional.entity.bakcoffice;

import id.co.bni.maverick.promoworkernotification.additional.entity.BaseEntity;
import id.co.bni.maverick.promoworkernotification.additional.entity.enums.Gender;
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
@Table(name = "promo_base")
@ToString(onlyExplicitlyIncluded = true)
public class PromoBase extends BaseEntity {
    @Id
    @Column(nullable = false, length = 21, updatable = false)
    private String id;

    private String promoId;

    private String mccGroup;

    private String merchantCategory;

    private String religion;

    private Gender gender;

    private String rulesRanking;

    private ZonedDateTime startDate;

    private ZonedDateTime endDate;
}
