package lk.agri.controller;

import lk.agri.entity.Chat;
import lk.agri.entity.Item;
import lk.agri.entity.CartDetail;
import lk.agri.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.web.csrf.CsrfToken;

import java.util.Random;

@CrossOrigin
@RestController
@RequestMapping(value = "farmer")
public class FarmerController {

    @Autowired
    private FarmerService itemService;

    @GetMapping(value="/csrf-token")
    public String getCsrfToken() {
//        System.out.println(token);
        String token=(new Random().nextInt(10000)+1)+"-"+(new Random().nextInt(10000)+1)+"-"+(new Random().nextInt(10000)+1)+"-"+(new Random().nextInt(10000)+1);
        return token;
    }
    @PostMapping(value = "/addItem")
    public ResponseEntity addItem(@RequestPart("item") Item item, @RequestParam(value = "file", required = false) MultipartFile file) {
        return ResponseEntity.ok(itemService.addItem(item, file));
    }
    @PostMapping(value = "/addChat")
    public ResponseEntity addChat(@RequestBody Chat chat) {
        return ResponseEntity.ok(itemService.addChat(chat));
    }

    @GetMapping(value = "/getchat")
    public ResponseEntity getAllChats(){
        return ResponseEntity.ok(itemService.getChat());
    }

    @GetMapping(value = "/getItems/{email}")
    public ResponseEntity getItems(@PathVariable String email) {
        return ResponseEntity.ok(itemService.getItems(email));
    }

    @PutMapping(value = "/updateItem/{id}")
    public ResponseEntity updateItem(@PathVariable String id, @RequestPart("item") Item item, @RequestParam(value = "file", required = false) MultipartFile file) {
        return ResponseEntity.ok(itemService.updateItem(id, item, file));
    }

    @DeleteMapping(value = "/removeItem/{id}")
    public ResponseEntity removeItem(@PathVariable String id) {
        return ResponseEntity.ok(itemService.removeItem(id));
    }

}
