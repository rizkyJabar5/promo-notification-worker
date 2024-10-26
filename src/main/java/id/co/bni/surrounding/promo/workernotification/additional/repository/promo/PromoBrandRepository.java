package id.co.bni.surrounding.promo.workernotification.additional.repository.promo;

import id.co.bni.surrounding.promo.workernotification.additional.entity.promo.PromoBrand;
import id.co.bni.surrounding.promo.workernotification.additional.repository.ReadOnlyRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PromoBrandRepository extends ReadOnlyRepository<PromoBrand, String> {

//    @Query("""
//            select pb
//            from PromoBrand pb
//            join MasterPromo mp
//                on upper(pb.mccGroup) = upper(p.mccGroup)
//                and upper(pb.merchantCategory) = upper(p.merchantCategory)
//                and p.promoId in :promoIds
//            join MasterUser u
//                on u.userId = t.userId
//                and u.gender = p.gender
//                and u.religion = p.religion
//                and u.segmentation = p.segmentation
//                and u.totalAsset is not null
//                and u.accountAge is not null
//                and u.transactionTime is not null
//            order by t.ranking desc
//            """)
//    List<PromoBrand> findByMccGroupAndMerchantCategory(@Param("promoIds") List<String> promoIds);
}
