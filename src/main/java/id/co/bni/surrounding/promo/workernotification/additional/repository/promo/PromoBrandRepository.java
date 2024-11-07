package id.co.bni.surrounding.promo.workernotification.additional.repository.promo;

import id.co.bni.surrounding.promo.workernotification.additional.entity.promo.PromoBrand;
import id.co.bni.surrounding.promo.workernotification.additional.repository.ReadOnlyRepository;

public interface PromoBrandRepository extends ReadOnlyRepository<PromoBrand, String> {

//    @Query("""
//            select pb
//            from PromoBrand pb
//            where pb.userId in :userIds
//            and pb.brand = :brand
//            and pb.categoryBO = :categoryBO
//            """)
//    List<PromoBrand> findAvailablePromoInUsers(@Param("userIds") List<String> userIds,
//                                               @Param("promoId") String promoId,
//                                               @Param("categoryBO") Integer categoryBO);
}
