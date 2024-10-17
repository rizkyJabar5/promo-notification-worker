package id.co.bni.maverick.promoworkernotification.controller;

import id.co.bni.maverick.promoworkernotification.model.BaseResponse;
import id.co.bni.maverick.promoworkernotification.service.PromoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/promo")
@RequiredArgsConstructor
public class PromoEventController {

    private final PromoService promoService;

    @GetMapping("/blast-promo")
    public ResponseEntity<BaseResponse> blastPromo() {
        return ResponseEntity.ok(promoService.sendPromo());
    }
}
