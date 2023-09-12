package sefakpsz.allInOnce.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import sefakpsz.allInOnce.daos.Category.CategoryDao;
import sefakpsz.allInOnce.daos.Order.OrderCreateDao;
import sefakpsz.allInOnce.daos.Order.OrderDao;
import sefakpsz.allInOnce.daos.Order.OrderUpdateProductsDao;
import sefakpsz.allInOnce.daos.Order.OrderUpdateStatusDao;
import sefakpsz.allInOnce.daos.Product.ProductDao;
import sefakpsz.allInOnce.daos.User.UserDao;
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

    public Result Create(OrderCreateDao dao) {
        var user = GettingUser.Get();

        var ordersOfUser = repository.findOrdersByUser(user);
        var ordersOfUserSize = ordersOfUser.size();

        if (ordersOfUserSize > 0) {
            var lastOrderOfUser = (Order) ordersOfUser.toArray()[ordersOfUserSize - 1];

            if (lastOrderOfUser.getStatus() == OrderStatus.Waiting)
                return new ErrorResult(messages.order_already_exists);
        }
        if (!controlOfProductExistence(dao.getProductIds()))
            return new ErrorResult(messages.product_not_found);

        var order = new Order();
        order.setUser(user);

        repository.save(order);

        for (var productId : dao.getProductIds()) {
            var productOrders = new ProductOrders();

            productOrders.setOrder(order);

            var product = productRepository.findProductById(productId);

            productOrders.setProduct(product);

            productOrdersRepository.save(productOrders);
        }

        return new SuccessResult(messages.success);
    }

    public Result UpdateProducts(OrderUpdateProductsDao dao) {
        var user = GettingUser.Get();

        var orderFromDb = repository.findById(dao.getOrderId());

        if (orderFromDb.isEmpty())
            return new ErrorResult(messages.order_not_found);

        if (orderFromDb.get().getStatus() != OrderStatus.Waiting)
            return new ErrorResult(messages.order_cant_update);

        if (!controlOfProductExistence(dao.getProductIds()))
            return new ErrorResult(messages.product_not_found);

        var productOrders = productOrdersRepository.findProductOrdersByOrderId(dao.getOrderId());

        /*for (var productOrder : productOrders) {
            if (!dao.getProductIds().contains(productOrder.getProductId())) {
                productOrdersRepository.delete(productOrder);
            }
        }*/

        /*for (var product : dao.getProductIds()) {
            var productOrderFromDb = productOrdersRepository.findProductOrdersByProductId(product);
            if (productOrderFromDb == null) {
                var productOrder = new ProductOrders();

                productOrder.setProductId(product);
                productOrder.setOrderId(dao.getOrderId());

                productOrdersRepository.save(productOrder);
            }
        }*/

        return new SuccessResult(messages.success);
    }

    public Result UpdateStatus(OrderUpdateStatusDao dao) {
        var user = GettingUser.Get();

        var orderFromDb = repository.findById(dao.getId());

        if (orderFromDb.isEmpty())
            return new ErrorResult(messages.order_not_found);

        if (orderFromDb.get().getStatus() != OrderStatus.Waiting)
            return new ErrorResult(messages.order_cant_update);

        orderFromDb.get().setStatus(dao.getStatus());

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

    public DataResult<ArrayList<OrderDao>> GetList() {
        var categories = repository.findAll();

        var orderList = new ArrayList<OrderDao>();
        for (var order : categories) {
            var orderDao = new OrderDao();

            var orderUser = order.getUser();
            var userDao = new UserDao();

            userDao.setId(orderUser.getId());
            userDao.setRole(orderUser.getRole());
            userDao.setLastname(orderUser.getLastname());
            userDao.setFirstname(orderUser.getFirstname());
            userDao.setEmail(orderUser.getEmail());

            orderDao.setId(order.getId());
            orderDao.setStatus(order.getStatus());
            orderDao.setUser(userDao);
            orderDao.setCreatedDate(order.getCreatedDate());
            orderDao.setModifiedDate(order.getModifiedDate());

            orderDao.setProducts(mappingProductsOfOrder(order));

            orderList.add(orderDao);
        }

        return new SuccessDataResult<ArrayList<OrderDao>>(orderList, messages.success);
    }

    public DataResult<OrderDao> GetById(Integer orderId) {
        var order = repository.findById(orderId);

        if (order.isEmpty())
            return new ErrorDataResult<OrderDao>(null, messages.order_not_found);

        var orderDao = new OrderDao();
        var orderUser = order.get().getUser();

        var userDao = new UserDao();

        userDao.setId(orderUser.getId());
        userDao.setRole(orderUser.getRole());
        userDao.setLastname(orderUser.getLastname());
        userDao.setFirstname(orderUser.getFirstname());
        userDao.setEmail(orderUser.getEmail());

        orderDao.setModifiedDate(order.get().getModifiedDate());
        orderDao.setCreatedDate(order.get().getCreatedDate());
        orderDao.setId(order.get().getId());
        orderDao.setStatus(order.get().getStatus());
        orderDao.setUser(userDao);

        orderDao.setProducts(mappingProductsOfOrder(order.get()));

        return new SuccessDataResult<OrderDao>(orderDao, messages.success);
    }

    private boolean controlOfProductExistence(List<Integer> productIds) {
        for (var product : productIds) {
            var productExists = productRepository.findProductById(product);

            if (productExists == null)
                return false;
        }

        return true;
    }

    private List<ProductDao> mappingProductsOfOrder(Order order) {
        var productDaos = new ArrayList<ProductDao>();
        var orderProducts = order.getProductOrders();

        for (var orderProduct : orderProducts) {
            var product = orderProduct.getProduct();

            var productDao = new ProductDao(
                    product.getId(),
                    product.getTitle(),
                    product.getPrice(),
                    product.getImageURL(),
                    null,
                    product.getModifiedDate(),
                    product.getCreatedDate()
            );

            if (product.getCategory() != null) {
                productDao.setCategory(new CategoryDao());

                productDao.getCategory().setId(product.getCategory().getId());
                productDao.getCategory().setTitle(product.getCategory().getTitle());
                productDao.getCategory().setImageURL(product.getCategory().getImageURL());
                productDao.getCategory().setCreatedDate(product.getCategory().getCreatedDate());
                productDao.getCategory().setModifiedDate(product.getCategory().getModifiedDate());
            }

            productDaos.add(productDao);
        }
        return productDaos;
    }
}
