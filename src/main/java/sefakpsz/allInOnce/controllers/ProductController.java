package sefakpsz.allInOnce.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sefakpsz.allInOnce.dtos.Category.CategoryCreateDto;
import sefakpsz.allInOnce.dtos.Category.CategoryUpdateDto;
import sefakpsz.allInOnce.dtos.Product.ProductCreateDto;
import sefakpsz.allInOnce.dtos.Product.ProductUpdateDto;
import sefakpsz.allInOnce.services.ProductService;
import sefakpsz.allInOnce.utils.results.Result;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping("/create")
    public ResponseEntity<Result> create(@Valid @RequestBody ProductCreateDto Dto) {
        var result = service.Create(Dto);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @PutMapping("/update")
    public ResponseEntity<Result> update(@Valid @RequestBody ProductUpdateDto Dto) {
        var result = service.Update(Dto);
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