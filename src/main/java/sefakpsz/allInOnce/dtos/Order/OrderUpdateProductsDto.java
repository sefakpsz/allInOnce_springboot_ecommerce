package sefakpsz.allInOnce.dtos.Order;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sefakpsz.allInOnce.enums.Order.OrderStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdateProductsDto {
    @NotNull
    private Integer orderId;

    @NotEmpty
    private List<Integer> productIds;
}
