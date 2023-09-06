package sefakpsz.allInOnce.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sefakpsz.allInOnce.daos.Product.ProductDao;
import sefakpsz.allInOnce.repositories.ProductRepository;
import sefakpsz.allInOnce.utils.results.DataResult;
import sefakpsz.allInOnce.utils.results.Result;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public Result Create(ProductDao dao) {
        return null;
    }

    public Result Update(ProductDao dao) {
        return null;
    }

    public Result Delete(Integer ProductId) {
        return null;
    }

    public DataResult<List<ProductDao>> GetList() {
        return null;
    }

    public Result GetById(Integer ProductId) {
        return null;
    }
}
