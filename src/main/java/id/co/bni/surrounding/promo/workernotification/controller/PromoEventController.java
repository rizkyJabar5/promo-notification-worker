package id.co.bni.surrounding.promo.workernotification.controller;

import id.co.bni.surrounding.promo.workernotification.model.BaseResponse;
import id.co.bni.surrounding.promo.workernotification.model.request.BlastPromoRequest;
import id.co.bni.surrounding.promo.workernotification.service.PromoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("promo-engine/v1")
@RequiredArgsConstructor
public class PromoEventController {

    private final PromoService promoService;

    @PostMapping("/promo")
    public ResponseEntity<BaseResponse> blastPromo(@RequestBody BlastPromoRequest request) {
        return ResponseEntity.ok(promoService.broadcastPromo(request.promoIds()));
    }
}
