package sefakpsz.allInOnce.dtos.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sefakpsz.allInOnce.dtos.Product.ProductDto;
import sefakpsz.allInOnce.dtos.User.UserDto;
import sefakpsz.allInOnce.enums.Order.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Integer id;
    private OrderStatus status;
    private List<ProductDto> products;
    private UserDto user;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
