package id.co.bni.surrounding.promo.workernotification.additional.repository.promo;

import id.co.bni.surrounding.promo.workernotification.additional.entity.promo.MasterPromo;
import id.co.bni.surrounding.promo.workernotification.additional.repository.ReadOnlyRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MasterPromoRepository extends ReadOnlyRepository<MasterPromo, String> {
    @Query("""
            select mp
            from MasterPromo mp
            where mp.promoId = ?1
            and mp.isDeleted = false
            """)
    Optional<MasterPromo> findPromoById(String promoId);
}