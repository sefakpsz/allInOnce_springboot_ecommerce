package sefakpsz.allInOnce.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sefakpsz.allInOnce.daos.User.UserChangePasswordDao;
import sefakpsz.allInOnce.services.UserService;
import sefakpsz.allInOnce.utils.results.DataResult;
import sefakpsz.allInOnce.utils.results.Result;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/getMyInfo")
    public ResponseEntity<DataResult> getMyInfo() {
        return ResponseEntity.ok(service.GetMyInfo());
    }
    @PutMapping("/changePassword")
    public ResponseEntity<Result> register(
            @RequestBody UserChangePasswordDao request
    ) {
        return ResponseEntity.ok(service.ChangePassword(request));
    }
}
