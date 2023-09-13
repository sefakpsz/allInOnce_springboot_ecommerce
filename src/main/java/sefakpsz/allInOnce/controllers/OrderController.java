package sefakpsz.allInOnce.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sefakpsz.allInOnce.dtos.Order.OrderCreateDto;
import sefakpsz.allInOnce.dtos.Order.OrderUpdateProductsDto;
import sefakpsz.allInOnce.dtos.Order.OrderUpdateStatusDto;
import sefakpsz.allInOnce.services.OrderService;
import sefakpsz.allInOnce.utils.results.Result;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping("/create")
    public ResponseEntity<Result> create(@Valid @RequestBody OrderCreateDto Dto) {
        var result = service.Create(Dto);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @PutMapping("/updateProducts")
    public ResponseEntity<Result> update(@Valid @RequestBody OrderUpdateProductsDto Dto) {
        var result = service.UpdateProducts(Dto);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<Result> update(@Valid @RequestBody OrderUpdateStatusDto Dto) {
        var result = service.UpdateStatus(Dto);
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
