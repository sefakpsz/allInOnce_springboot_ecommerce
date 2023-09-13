package sefakpsz.allInOnce.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import sefakpsz.allInOnce.dtos.Category.CategoryDto;
import sefakpsz.allInOnce.dtos.Order.OrderCreateDto;
import sefakpsz.allInOnce.dtos.Order.OrderDto;
import sefakpsz.allInOnce.dtos.Order.OrderUpdateProductsDto;
import sefakpsz.allInOnce.dtos.Order.OrderUpdateStatusDto;
import sefakpsz.allInOnce.dtos.Product.ProductDto;
import sefakpsz.allInOnce.dtos.User.UserDto;
import sefakpsz.allInOnce.entities.Order;
import sefakpsz.allInOnce.entities.Product;
import sefakpsz.allInOnce.entities.ProductOrders;
import sefakpsz.allInOnce.enums.Order.OrderStatus;
import sefakpsz.allInOnce.repositories.OrderRepository;
import sefakpsz.allInOnce.repositories.ProductOrdersRepository;
import sefakpsz.allInOnce.repositories.ProductRepository;
import sefakpsz.allInOnce.utils.constants.messages;
import sefakpsz.allInOnce.utils.functions.GettingUser;
import sefakpsz.allInOnce.utils.results.*;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final ProductRepository productRepository;
    private final ProductOrdersRepository productOrdersRepository;

    public Result Create(OrderCreateDto Dto) {
        var user = GettingUser.Get();

        var ordersOfUser = repository.findOrdersByUser(user);
        var ordersOfUserSize = ordersOfUser.size();

        if (ordersOfUserSize > 0) {
            var lastOrderOfUser = (Order) ordersOfUser.toArray()[ordersOfUserSize - 1];

            if (lastOrderOfUser.getStatus() == OrderStatus.Waiting)
                return new ErrorResult(messages.order_already_exists);
        }
        if (!controlOfProductExistence(Dto.getProductIds()))
            return new ErrorResult(messages.product_not_found);

        var order = new Order();
        order.setUser(user);

        repository.save(order);

        for (var productId : Dto.getProductIds()) {
            var productOrders = new ProductOrders();

            productOrders.setOrder(order);

            var product = productRepository.findProductById(productId);

            productOrders.setProduct(product);

            productOrdersRepository.save(productOrders);
        }

        return new SuccessResult(messages.success);
    }

    public Result UpdateProducts(OrderUpdateProductsDto Dto) {
        var user = GettingUser.Get();

        var orderFromDb = repository.findById(Dto.getOrderId());

        if (orderFromDb.isEmpty())
            return new ErrorResult(messages.order_not_found);

        if (orderFromDb.get().getStatus() != OrderStatus.Waiting)
            return new ErrorResult(messages.order_cant_update);

        if (!controlOfProductExistence(Dto.getProductIds()))
            return new ErrorResult(messages.product_not_found);

        var productOrders = productOrdersRepository.findProductOrdersByOrderId(Dto.getOrderId());

        /*for (var productOrder : productOrders) {
            if (!Dto.getProductIds().contains(productOrder.getProductId())) {
                productOrdersRepository.delete(productOrder);
            }
        }*/

        /*for (var product : Dto.getProductIds()) {
            var productOrderFromDb = productOrdersRepository.findProductOrdersByProductId(product);
            if (productOrderFromDb == null) {
                var productOrder = new ProductOrders();

                productOrder.setProductId(product);
                productOrder.setOrderId(Dto.getOrderId());

                productOrdersRepository.save(productOrder);
            }
        }*/

        return new SuccessResult(messages.success);
    }

    public Result UpdateStatus(OrderUpdateStatusDto Dto) {
        var user = GettingUser.Get();

        var orderFromDb = repository.findById(Dto.getId());

        if (orderFromDb.isEmpty())
            return new ErrorResult(messages.order_not_found);

        if (orderFromDb.get().getStatus() != OrderStatus.Waiting)
            return new ErrorResult(messages.order_cant_update);

        orderFromDb.get().setStatus(Dto.getStatus());

        repository.save(orderFromDb.get());

        return new SuccessResult(messages.success);
    }

    public Result Delete(Integer OrderId) {
        var Order = repository.findById(OrderId);

        if (Order.isEmpty())
            return new ErrorResult(messages.order_not_found);

        repository.delete(Order.get());

        return new SuccessResult(messages.success);
    }

    public DataResult<ArrayList<OrderDto>> GetList() {
        var categories = repository.findAll();

        var orderList = new ArrayList<OrderDto>();
        for (var order : categories) {
            var orderDto = new OrderDto();

            var orderUser = order.getUser();
            var userDto = new UserDto();

            userDto.setId(orderUser.getId());
            userDto.setRole(orderUser.getRole());
            userDto.setLastname(orderUser.getLastname());
            userDto.setFirstname(orderUser.getFirstname());
            userDto.setEmail(orderUser.getEmail());

            orderDto.setId(order.getId());
            orderDto.setStatus(order.getStatus());
            orderDto.setUser(userDto);
            orderDto.setCreatedDate(order.getCreatedDate());
            orderDto.setModifiedDate(order.getModifiedDate());

            orderDto.setProducts(mappingProductsOfOrder(order));

            orderList.add(orderDto);
        }

        return new SuccessDataResult<ArrayList<OrderDto>>(orderList, messages.success);
    }

    public DataResult<OrderDto> GetById(Integer orderId) {
        var order = repository.findById(orderId);

        if (order.isEmpty())
            return new ErrorDataResult<OrderDto>(null, messages.order_not_found);

        var orderDto = new OrderDto();
        var orderUser = order.get().getUser();

        var userDto = new UserDto();

        userDto.setId(orderUser.getId());
        userDto.setRole(orderUser.getRole());
        userDto.setLastname(orderUser.getLastname());
        userDto.setFirstname(orderUser.getFirstname());
        userDto.setEmail(orderUser.getEmail());

        orderDto.setModifiedDate(order.get().getModifiedDate());
        orderDto.setCreatedDate(order.get().getCreatedDate());
        orderDto.setId(order.get().getId());
        orderDto.setStatus(order.get().getStatus());
        orderDto.setUser(userDto);

        orderDto.setProducts(mappingProductsOfOrder(order.get()));

        return new SuccessDataResult<OrderDto>(orderDto, messages.success);
    }

    private boolean controlOfProductExistence(List<Integer> productIds) {
        for (var product : productIds) {
            var productExists = productRepository.findProductById(product);

            if (productExists == null)
                return false;
        }

        return true;
    }

    private List<ProductDto> mappingProductsOfOrder(Order order) {
        var productdtos = new ArrayList<ProductDto>();
        var orderProducts = order.getProductOrders();

        for (var orderProduct : orderProducts) {
            var product = orderProduct.getProduct();

            var productDto = new ProductDto(
                    product.getId(),
                    product.getTitle(),
                    product.getPrice(),
                    product.getImageURL(),
                    null,
                    product.getModifiedDate(),
                    product.getCreatedDate()
            );

            if (product.getCategory() != null) {
                productDto.setCategory(new CategoryDto());

                productDto.getCategory().setId(product.getCategory().getId());
                productDto.getCategory().setTitle(product.getCategory().getTitle());
                productDto.getCategory().setImageURL(product.getCategory().getImageURL());
                productDto.getCategory().setCreatedDate(product.getCategory().getCreatedDate());
                productDto.getCategory().setModifiedDate(product.getCategory().getModifiedDate());
            }

            productdtos.add(productDto);
        }
        return productdtos;
    }
}
