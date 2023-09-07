package sefakpsz.allInOnce.controllers;

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
    public ResponseEntity<Result> create(@RequestBody ProductCreateDao dao) {
        return ResponseEntity.ok(service.Create(dao));
    }

    @PutMapping("/update")
    public ResponseEntity<Result> update(@RequestBody ProductUpdateDao dao) {
        return ResponseEntity.ok(service.Update(dao));
    }

    @GetMapping("/getList")
    public ResponseEntity<Result> getList() {
        return ResponseEntity.ok(service.GetList());
    }

    @GetMapping("/getById")
    public ResponseEntity<Result> getById(@RequestParam Integer productId) {
        return ResponseEntity.ok(service.GetById(productId));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@RequestParam Integer productId) {
        return ResponseEntity.ok(service.Delete(productId));
    }
}