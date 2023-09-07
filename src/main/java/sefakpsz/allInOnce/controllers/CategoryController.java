package sefakpsz.allInOnce.controllers;

import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Result> create(@RequestBody CategoryCreateDao dao) {
        return ResponseEntity.ok(service.Create(dao));
    }

    @PutMapping("/update")
    public ResponseEntity<Result> update(@RequestBody CategoryUpdateDao dao) {
        return ResponseEntity.ok(service.Update(dao));
    }

    @GetMapping("/getList")
    public ResponseEntity<Result> getList() {
        return ResponseEntity.ok(service.GetList());
    }

    @GetMapping("/getById")
    public ResponseEntity<Result> getById(@RequestParam Integer categoryId) {
        return ResponseEntity.ok(service.GetById(categoryId));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@RequestParam Integer categoryId) {
        return ResponseEntity.ok(service.Delete(categoryId));
    }
}
