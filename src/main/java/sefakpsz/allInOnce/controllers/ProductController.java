package sefakpsz.allInOnce.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sefakpsz.allInOnce.daos.Category.CategoryCreateDao;
import sefakpsz.allInOnce.daos.Category.CategoryUpdateDao;
import sefakpsz.allInOnce.daos.Product.ProductCreateDao;
import sefakpsz.allInOnce.daos.Product.ProductUpdateDao;
import sefakpsz.allInOnce.services.ProductService;
import sefakpsz.allInOnce.utils.results.Result;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping("/create")
    public ResponseEntity<Result> create(@Valid @RequestBody ProductCreateDao dao) {
        var result = service.Create(dao);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @PutMapping("/update")
    public ResponseEntity<Result> update(@Valid @RequestBody ProductUpdateDao dao) {
        var result = service.Update(dao);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @GetMapping("/getList")
    public ResponseEntity<Result> getList() {
        var result = service.GetList();
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @GetMapping("/getById")
    public ResponseEntity<Result> getById(@Valid @RequestParam @NotNull Integer productId) {
        var result = service.GetById(productId);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@Valid @RequestParam @NotNull Integer productId) {
        var result = service.Delete(productId);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }
}