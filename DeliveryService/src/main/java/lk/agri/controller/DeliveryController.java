package lk.agri.controller;

import lk.agri.entity.Cart;
import lk.agri.entity.CartDetail;
import lk.agri.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping(value = "/getDeliveries")
    public ResponseEntity getDeliveries() {
        return ResponseEntity.ok(deliveryService.getDeliveries());
    }

}
