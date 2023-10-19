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

    @GetMapping(value = "/chkToken/{email}")
    public ResponseEntity chkToken(@PathVariable String email) {
        return ResponseEntity.ok(userAccountService.loggedUser(email));
    }

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody UserAccount userAccount) {
        return ResponseEntity.ok(userAccountService.login(userAccount));
    }

    @GetMapping(value = "/user/{txt}")
    public ResponseEntity loggedUser(@PathVariable String txt) {
        return ResponseEntity.ok(userAccountService.loggedUser(txt));
    }

    @PostMapping(value = "/signUp")
    public ResponseEntity signUp(@RequestBody UserAccount userAccount) {
        return ResponseEntity.ok(userAccountService.signUp(userAccount));
    }

    @PostMapping(value = "/login_google")
    public ResponseEntity loginGoogle(@RequestBody UserAccount userAccount) {
        return ResponseEntity.ok(userAccountService.loginGoogle(userAccount));
    }
}
