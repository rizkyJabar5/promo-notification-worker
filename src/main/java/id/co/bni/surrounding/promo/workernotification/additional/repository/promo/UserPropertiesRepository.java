package id.co.bni.surrounding.promo.workernotification.additional.repository.promo;

import id.co.bni.surrounding.promo.workernotification.additional.entity.promo.MasterPromo;
import id.co.bni.surrounding.promo.workernotification.additional.entity.promo.UserProperties;
import id.co.bni.surrounding.promo.workernotification.additional.repository.ReadOnlyRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserPropertiesRepository extends ReadOnlyRepository<UserProperties, String> {

    @Query("""
            select u
            from UserProperties u
            join PromoBrand pb
                on upper(pb.brand) = upper(:#{#masterPromo.brand})
                and pb.categoryBO = :#{#masterPromo.category}
            join PromoSegmentation ps
                on ps.segmentation = u.segmentation
                and ps.categoryBO = pb.categoryBO
            where u.gender = :#{#masterPromo.gender}
            and u.religion = :#{#masterPromo.religion}
            and u.segmentation = :#{#masterPromo.segmentation}
            and u.aum = :#{#masterPromo.aum}
            and u.accountAge = :#{#masterPromo.accountAge}
            and u.transactionTime = :#{#masterPromo.transactionTime}
            """)
    List<UserProperties> findExactPromoUser(MasterPromo masterPromo);

    @Query("""
            select u
            from UserProperties u
            join PromoBrand pb
                on upper(pb.brand) = upper(:#{#masterPromo.brand})
                and pb.categoryBO = :#{#masterPromo.category}
            join PromoSegmentation ps
                on ps.segmentation = u.segmentation
                and ps.categoryBO = pb.categoryBO
            """)
    List<UserProperties> findExactPromoUserNonFilterByProperties(MasterPromo masterPromo);
}