package lk.agri.controller;

import lk.agri.entity.UserAccount;
import lk.agri.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "user")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody UserAccount userAccount) {
        return ResponseEntity.ok(userAccountService.login(userAccount));
    }

    @PostMapping(value = "/signUp")
    public ResponseEntity signUp(@RequestBody UserAccount userAccount) {
        return ResponseEntity.ok(userAccountService.signUp(userAccount));
    }
}
