package sefakpsz.allInOnce.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sefakpsz.allInOnce.daos.Category.CategoryDao;
import sefakpsz.allInOnce.daos.Product.ProductCreateDao;
import sefakpsz.allInOnce.daos.Product.ProductDao;
import sefakpsz.allInOnce.daos.Product.ProductUpdateDao;
import sefakpsz.allInOnce.daos.Product.ProductUpdateDao;
import sefakpsz.allInOnce.entities.Product;
import sefakpsz.allInOnce.repositories.CategoryRepository;
import sefakpsz.allInOnce.repositories.ProductRepository;
import sefakpsz.allInOnce.utils.constants.messages;
import sefakpsz.allInOnce.utils.results.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    public Result Create(ProductCreateDao dao) {
        var titleExists = repository.findProductByTitle(dao.getTitle());

        if (titleExists != null)
            return new ErrorResult(messages.product_already_exists);

        var category = categoryRepository.findById(dao.getCategoryId());

        if (category.isEmpty())
            return new ErrorResult(messages.category_not_found);

        var product = new Product();

        product.setTitle(dao.getTitle());
        product.setImageURL(dao.getImageURL());
        product.setPrice(dao.getPrice());
        product.setCategory(category.get());

        repository.save(product);

        return new SuccessResult(messages.success);
    }

    public Result Update(ProductUpdateDao dao) {
        var product = repository.findById(dao.getId());

        if (product.isEmpty())
            return new ErrorResult(messages.product_not_found);

        var titleExists = repository.findProductByTitle(dao.getTitle());

        if (titleExists != null)
            return new ErrorResult(messages.product_already_exists);

        product.get().setTitle(dao.getTitle());
        product.get().setImageURL(dao.getImageURL());
        product.get().setPrice(dao.getPrice());
        product.get().setModifiedDate(LocalDateTime.now());

        repository.save(product.get());

        return new SuccessResult(messages.success);
    }

    public Result Delete(Integer ProductId) {
        var product = repository.findById(ProductId);

        if (product.isEmpty())
            return new ErrorResult(messages.product_not_found);

        repository.delete(product.get());

        return new SuccessResult(messages.success);
    }

    public DataResult<List<ProductDao>> GetList() {
        var products = repository.findAll();

        var ProductList = new ArrayList<ProductDao>();
        for (var product : products) {
            var categoryOfProduct = product.getCategory();

            var productDao = new ProductDao();

            if (categoryOfProduct != null) {
                var categoryDao = new CategoryDao(
                        categoryOfProduct.getId(),
                        categoryOfProduct.getTitle(),
                        categoryOfProduct.getImageURL(),
                        categoryOfProduct.getCreatedDate(),
                        categoryOfProduct.getModifiedDate()
                );

                productDao.setCategory(categoryDao);
            }

            productDao.setId(product.getId());
            productDao.setTitle(product.getTitle());
            productDao.setImageURL(product.getImageURL());
            productDao.setPrice(product.getPrice());
            productDao.setCreatedDate(product.getCreatedDate());
            productDao.setModifiedDate(product.getModifiedDate());

            ProductList.add(productDao);
        }

        return new SuccessDataResult<List<ProductDao>>(ProductList, messages.success);
    }

    public DataResult<ProductDao> GetById(Integer ProductId) {
        var product = repository.findById(ProductId);

        if (product.isEmpty())
            return new ErrorDataResult<ProductDao>(null, messages.product_not_found);

        var categoryOfProduct = product.get().getCategory();

        var productDao = new ProductDao();

        if (categoryOfProduct != null) {
            var categoryDao = new CategoryDao(
                    categoryOfProduct.getId(),
                    categoryOfProduct.getTitle(),
                    categoryOfProduct.getImageURL(),
                    categoryOfProduct.getCreatedDate(),
                    categoryOfProduct.getModifiedDate()
            );

            productDao.setCategory(categoryDao);
        }

        productDao.setId(product.get().getId());
        productDao.setTitle(product.get().getTitle());
        productDao.setImageURL(product.get().getImageURL());
        productDao.setPrice(product.get().getPrice());
        productDao.setCreatedDate(product.get().getCreatedDate());
        productDao.setModifiedDate(product.get().getModifiedDate());

        return new SuccessDataResult<ProductDao>(productDao, messages.success);
    }
}
