package lk.agri.controller;

import lk.agri.Security.Encryption;
import lk.agri.entity.Cart;
import lk.agri.entity.CartDetail;
import lk.agri.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "buyer")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @GetMapping(value = "/getItems/{txt}")
    public ResponseEntity getItems(@PathVariable String txt) {
        return ResponseEntity.ok(buyerService.getItems(txt));
    }

    @PostMapping(value = "/addToCart")
    public ResponseEntity addToCart(@RequestBody CartDetail cart) {
        return ResponseEntity.ok(buyerService.addToCart(cart));
    }

    @PostMapping(value = "/addCart")
    public ResponseEntity addCart(@RequestBody Cart cart) {
        return ResponseEntity.ok(buyerService.addCart(cart));
    }
//    @GetMapping("/getCart")
//    public ResponseEntity getCart(@RequestParam("email") String email) {
//        System.out.printf(email);
//        try {
//            return ResponseEntity.ok(buyerService.getCart(email));
//        } catch (Exception e) {
//
//            return null;
//            // Handle decryption error or other exceptions
//           // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email parameter");
//        }
//    }
    @GetMapping(value = "/getCart/{email}")
    public ResponseEntity getCart(@PathVariable String email) {
        System.out.println(email);
        return ResponseEntity.ok(buyerService.getCart(email));
    }

    @DeleteMapping(value = "/removeCartDetail/{id}")
    public ResponseEntity removeCartDetail(@PathVariable String id) {
        return ResponseEntity.ok(buyerService.removeCartDetail(id));
    }

}
