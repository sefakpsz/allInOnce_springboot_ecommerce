package sefakpsz.allInOnce.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sefakpsz.allInOnce.daos.Auth.AuthLoginDao;
import sefakpsz.allInOnce.daos.Auth.AuthRegisterDao;
import sefakpsz.allInOnce.daos.Auth.AuthResponseDao;
import sefakpsz.allInOnce.services.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/signUp")
    public ResponseEntity<AuthResponseDao> register(
            @RequestBody AuthRegisterDao request
    ) {
        return ResponseEntity.ok(service.signUp(request));
    }

    @PostMapping("/signIn")
    public ResponseEntity<AuthResponseDao> login(
            @RequestBody AuthLoginDao request
    ) {
        return ResponseEntity.ok(service.signIn(request));
    }
}
