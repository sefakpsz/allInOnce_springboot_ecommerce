package sefakpsz.allInOnce.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
            @Valid @RequestBody UserChangePasswordDao request
    ) {
        return ResponseEntity.ok(service.ChangePassword(request));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@Valid @RequestParam @NotNull Integer userId) {
        return ResponseEntity.ok(service.Delete(userId));
    }
}
