package sefakpsz.allInOnce.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sefakpsz.allInOnce.daos.Order.OrderCreateDao;
import sefakpsz.allInOnce.daos.Order.OrderUpdateProductsDao;
import sefakpsz.allInOnce.daos.Order.OrderUpdateStatusDao;
import sefakpsz.allInOnce.services.OrderService;
import sefakpsz.allInOnce.utils.results.Result;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping("/create")
    public ResponseEntity<Result> create(@Valid @RequestBody OrderCreateDao dao) {
        var result = service.Create(dao);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @PutMapping("/updateProducts")
    public ResponseEntity<Result> update(@Valid @RequestBody OrderUpdateProductsDao dao) {
        var result = service.UpdateProducts(dao);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<Result> update(@Valid @RequestBody OrderUpdateStatusDao dao) {
        var result = service.UpdateStatus(dao);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @GetMapping("/getList")
    public ResponseEntity<Result> getList() {
        var result = service.GetList();
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @GetMapping("/getById")
    public ResponseEntity<Result> getById(@Valid @RequestParam @NotNull Integer orderId) {
        var result = service.GetById(orderId);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@Valid @RequestParam Integer orderId) {
        var result = service.Delete(orderId);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }
}
