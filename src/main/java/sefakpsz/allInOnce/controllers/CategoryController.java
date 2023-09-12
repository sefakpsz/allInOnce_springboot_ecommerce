package sefakpsz.allInOnce.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sefakpsz.allInOnce.daos.Category.CategoryCreateDao;
import sefakpsz.allInOnce.daos.Category.CategoryUpdateDao;
import sefakpsz.allInOnce.daos.User.UserChangePasswordDao;
import sefakpsz.allInOnce.services.CategoryService;
import sefakpsz.allInOnce.utils.results.DataResult;
import sefakpsz.allInOnce.utils.results.Result;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @PostMapping("/create")
    public ResponseEntity<Result> create(@Valid @RequestBody CategoryCreateDao dao) {
        var result = service.Create(dao);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @PutMapping("/update")
    public ResponseEntity<Result> update(@Valid @RequestBody CategoryUpdateDao dao) {
        var result = service.Update(dao);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @GetMapping("/getList")
    public ResponseEntity<Result> getList() {
        var result = service.GetList();
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @GetMapping("/getById")
    public ResponseEntity<Result> getById(@Valid @RequestParam @NotNull Integer categoryId) {
        var result = service.GetById(categoryId);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@Valid @RequestParam @NotNull Integer categoryId) {
        var result = service.Delete(categoryId);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }
}
