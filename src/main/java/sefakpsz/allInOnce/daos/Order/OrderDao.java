package sefakpsz.allInOnce.daos.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sefakpsz.allInOnce.daos.Product.ProductDao;
import sefakpsz.allInOnce.daos.User.UserDao;
import sefakpsz.allInOnce.enums.Order.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDao {
    private Integer id;
    private OrderStatus status;
    private List<ProductDao> products;
    private UserDao user;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
