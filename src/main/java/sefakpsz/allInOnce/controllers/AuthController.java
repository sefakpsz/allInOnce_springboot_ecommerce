package sefakpsz.allInOnce.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sefakpsz.allInOnce.dtos.Auth.AuthLoginDto;
import sefakpsz.allInOnce.dtos.Auth.AuthRegisterDto;
import sefakpsz.allInOnce.dtos.Auth.AuthResponseDto;
import sefakpsz.allInOnce.services.AuthService;
import sefakpsz.allInOnce.utils.results.DataResult;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/signUp")
    public ResponseEntity<DataResult<AuthResponseDto>> register(@Valid @RequestBody AuthRegisterDto request) {
        var result = service.signUp(request);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @PostMapping("/signIn")
    public ResponseEntity<DataResult<AuthResponseDto>> login(@Valid @RequestBody AuthLoginDto request) {
        var result = service.signIn(request);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }
}
