package sefakpsz.allInOnce.controllers;

import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Result> create(@RequestBody OrderCreateDao dao) {
        return ResponseEntity.ok(service.Create(dao));
    }

    @PutMapping("/updateProducts")
    public ResponseEntity<Result> update(@RequestBody OrderUpdateProductsDao dao) {
        return ResponseEntity.ok(service.UpdateProducts(dao));
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<Result> update(@RequestBody OrderUpdateStatusDao dao) {
        return ResponseEntity.ok(service.UpdateStatus(dao));
    }

    @GetMapping("/getList")
    public ResponseEntity<Result> getList() {
        return ResponseEntity.ok(service.GetList());
    }

    @GetMapping("/getById")
    public ResponseEntity<Result> getById(@RequestParam Integer orderId) {
        return ResponseEntity.ok(service.GetById(orderId));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@RequestParam Integer orderId) {
        return ResponseEntity.ok(service.Delete(orderId));
    }
}
