package sefakpsz.allInOnce.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sefakpsz.allInOnce.daos.Order.OrderDao;
import sefakpsz.allInOnce.repositories.OrderRepository;
import sefakpsz.allInOnce.utils.results.DataResult;
import sefakpsz.allInOnce.utils.results.Result;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;

    public Result Create(OrderDao dao) {
        return null;
    }

    public Result Update(OrderDao dao) {
        return null;
    }

    public Result Delete(Integer OrderId) {
        return null;
    }

    public DataResult<List<OrderDao>> GetList() {
        return null;
    }

    public Result GetById(Integer OrderId) {
        return null;
    }
}
