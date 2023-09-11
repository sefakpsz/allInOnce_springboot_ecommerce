package sefakpsz.allInOnce.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sefakpsz.allInOnce.daos.Category.CategoryCreateDao;
import sefakpsz.allInOnce.daos.Category.CategoryDao;
import sefakpsz.allInOnce.daos.Category.CategoryUpdateDao;
import sefakpsz.allInOnce.daos.Product.ProductDao;
import sefakpsz.allInOnce.entities.Category;
import sefakpsz.allInOnce.repositories.CategoryRepository;
import sefakpsz.allInOnce.repositories.ProductRepository;
import sefakpsz.allInOnce.utils.constants.messages;
import sefakpsz.allInOnce.utils.results.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    private final ProductRepository productRepository;

    public Result Create(CategoryCreateDao dao) {
        var category = new Category();

        category.setTitle(dao.getTitle());
        category.setImageURL(dao.getImageURL());

        var titleExists = repository.findCategoryByTitle(category.getTitle());

        if (titleExists != null)
            return new ErrorResult(messages.category_already_exists);

        repository.save(category);

        return new SuccessResult(messages.success);
    }

    public Result Update(CategoryUpdateDao dao) {
        var category = repository.findById(dao.getCategoryId());

        if (category.isEmpty())
            return new ErrorResult(messages.category_not_found);

        var titleExists = repository.findCategoryByTitle(dao.getTitle());

        if (titleExists != null)
            return new ErrorResult(messages.category_already_exists);

        category.get().setTitle(dao.getTitle());
        category.get().setImageURL(dao.getImageURL());
        category.get().setModifiedDate(LocalDateTime.now());

        repository.save(category.get());

        return new SuccessResult(messages.success);
    }

    public Result Delete(Integer categoryId) {
        var category = repository.findById(categoryId);

        if (category.isEmpty())
            return new ErrorResult(messages.category_not_found);

        var productsOfTheCategory = productRepository.findProductsByCategory(category.get());

        for (var product : productsOfTheCategory) {
            product.setCategory(null);

            productRepository.save(product);
        }

        repository.delete(category.get());

        return new SuccessResult(messages.success);
    }

    public DataResult<List<CategoryDao>> GetList() {
        var categories = repository.findAll();

        var categoryList = new ArrayList<CategoryDao>();
        for (var category : categories) {
            var categoryDao = new CategoryDao();

            categoryDao.setId(category.getId());
            categoryDao.setTitle(category.getTitle());
            categoryDao.setImageURL(category.getImageURL());
            categoryDao.setCreatedDate(category.getCreatedDate());
            categoryDao.setModifiedDate(category.getModifiedDate());

            categoryList.add(categoryDao);
        }

        return new SuccessDataResult<List<CategoryDao>>(categoryList, messages.success);
    }

    public DataResult<CategoryDao> GetById(Integer categoryId) {
        var category = repository.findById(categoryId);

        if (category.isEmpty())
            return new ErrorDataResult<CategoryDao>(null, messages.category_not_found);

        var categoryDao = new CategoryDao();

        var productOfCateogry = category.get().getProducts();

        var productsDao = new ArrayList<ProductDao>();

        for (var product : productOfCateogry) {
            var productDao = new ProductDao();

            productDao.setPrice(product.getPrice());
            productDao.setTitle(product.getTitle());
            productDao.setId(product.getId());
            productDao.setCreatedDate(product.getCreatedDate());
            productDao.setModifiedDate(product.getModifiedDate());
            productDao.setImageURL(product.getImageURL());

            productsDao.add(productDao);
        }

        categoryDao.setModifiedDate(category.get().getModifiedDate());
        categoryDao.setCreatedDate(category.get().getCreatedDate());
        categoryDao.setTitle(category.get().getTitle());
        categoryDao.setProducts(productsDao);
        categoryDao.setImageURL(category.get().getTitle());
        categoryDao.setId(category.get().getId());

        return new SuccessDataResult<CategoryDao>(categoryDao, messages.success);
    }
}
