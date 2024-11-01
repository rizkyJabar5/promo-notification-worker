package id.co.bni.surrounding.promo.workernotification.additional.repository.promo;

import id.co.bni.surrounding.promo.workernotification.additional.entity.promo.MasterPromo;
import id.co.bni.surrounding.promo.workernotification.additional.entity.promo.UserProperties;
import id.co.bni.surrounding.promo.workernotification.additional.repository.ReadOnlyRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserPropertiesRepository extends ReadOnlyRepository<UserProperties, String> {

    @Query("""
            select distinct u
            from UserProperties u
            join PromoBrand pb
                on upper(pb.brand) = upper(:#{masterPromo.brand})
                and pb.category = :#{masterPromo.category}
            join PromoSegmentation ps
                on ps.segmentation = u.segmentation
                and ps.category = pb.category
            where u.gender = :#{masterPromo.gender}
            and u.religion = :#{masterPromo.religion}
            and u.segmentation = :#{masterPromo.segmentation}
            and u.totalAsset = :#{masterPromo.totalAsset}
            and u.accountAge = :#{masterPromo.accountAge}
            and u.transactionTime = :#{masterPromo.transactionTime}
            """)
    List<UserProperties> findExactPromoUser(MasterPromo masterPromo);

    @Query("""
            select distinct u
            from UserProperties u
            join PromoBrand pb
                on upper(pb.brand) = upper(:#{masterPromo.brand})
                and pb.category = :#{masterPromo.category}
            join PromoSegmentation ps
                on ps.segmentation = u.segmentation
                and ps.category = pb.category
            """)
    List<UserProperties> findExactPromoUserNonFilterByProperties(MasterPromo masterPromo);
}