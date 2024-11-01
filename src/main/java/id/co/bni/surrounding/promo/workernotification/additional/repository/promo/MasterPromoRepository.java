package id.co.bni.surrounding.promo.workernotification.additional.repository.promo;

import id.co.bni.surrounding.promo.workernotification.additional.entity.promo.MasterPromo;
import id.co.bni.surrounding.promo.workernotification.additional.repository.ReadOnlyRepository;
import org.springframework.data.jpa.repository.Query;

public interface MasterPromoRepository extends ReadOnlyRepository<MasterPromo, String> {
    @Query("""
            select mp
            from MasterPromo mp
            where mp.id = ?1
            """)
    MasterPromo findPromoById(String promoId);
}