package id.co.bni.surrounding.promo.workernotification.additional.repository.promo;

import id.co.bni.surrounding.promo.workernotification.additional.entity.promo.MasterPromo;
import id.co.bni.surrounding.promo.workernotification.additional.projection.PromoProjection;
import id.co.bni.surrounding.promo.workernotification.additional.repository.ReadOnlyRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MasterPromoRepository extends ReadOnlyRepository<MasterPromo, String> {
    @Query("""
            select new PromoProjection (u.userId, mp.promoId, mp.type)
            from MasterPromo mp
            join PromoBrand pb
                on upper(pb.brand) = upper(mp.brand)
                and upper(pb.category) = upper(mp.category)
            join UserProperties u
                on u.userId = pb.userId
            join PromoSegmentation ps
                on ps.segmentation = u.segmentation
                and ps.category = pb.category
            where mp.promoId = :promoId
            group by pb.score
            """)
    List<PromoProjection> findExactPromoUser(@Param("promoId") String promoIds);
}