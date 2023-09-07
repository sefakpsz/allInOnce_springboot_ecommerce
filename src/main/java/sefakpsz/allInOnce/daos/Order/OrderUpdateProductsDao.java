package sefakpsz.allInOnce.daos.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sefakpsz.allInOnce.enums.Order.OrderStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdateProductsDao {
    private Integer orderId;
    private List<Integer> productIds;
}
