package id.co.bni.maverick.promoworkernotification.additional.entity.promo;

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

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "master_user")
@ToString(onlyExplicitlyIncluded = true)
public class MasterUser extends BaseEntity {
    @Id
    @Column(nullable = false, length = 21, updatable = false)
    private String id;

    @Column(name = "USER_ID")
    private String userId;

    private Gender gender;

    private String religion;

    private String segmentation;
}
