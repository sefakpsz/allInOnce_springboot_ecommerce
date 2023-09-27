package sefakpsz.allInOnce.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sefakpsz.allInOnce.dtos.Category.CategoryDto;
import sefakpsz.allInOnce.dtos.Product.ProductCreateDto;
import sefakpsz.allInOnce.dtos.Product.ProductDto;
import sefakpsz.allInOnce.dtos.Product.ProductUpdateDto;
import sefakpsz.allInOnce.entities.Product;
import sefakpsz.allInOnce.repositories.CategoryRepository;
import sefakpsz.allInOnce.repositories.ProductRepository;
import sefakpsz.allInOnce.utils.constants.Messages;
import sefakpsz.allInOnce.utils.results.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    public Result Create(ProductCreateDto Dto) {
        var titleExists = repository.findProductByTitle(Dto.getTitle());

        if (titleExists != null)
            return new ErrorResult(Messages.product_already_exists.toString());

        var category = categoryRepository.findById(Dto.getCategoryId());

        if (category.isEmpty())
            return new ErrorResult(Messages.category_not_found.toString());

        var product = new Product();

        product.setTitle(Dto.getTitle());
        product.setImageURL(Dto.getImageURL());
        product.setPrice(Dto.getPrice());
        product.setCategory(category.get());

        repository.save(product);

        return new SuccessResult(Messages.success.toString());
    }

    public Result Update(ProductUpdateDto Dto) {
        var product = repository.findById(Dto.getId());

        if (product.isEmpty())
            return new ErrorResult(Messages.product_not_found.toString());

        var titleExists = repository.findProductByTitle(Dto.getTitle());

        if (titleExists != null)
            return new ErrorResult(Messages.product_already_exists.toString());

        product.get().setTitle(Dto.getTitle());
        product.get().setImageURL(Dto.getImageURL());
        product.get().setPrice(Dto.getPrice());
        product.get().setModifiedDate(LocalDateTime.now());

        repository.save(product.get());

        return new SuccessResult(Messages.success.toString());
    }

    public Result Delete(Integer ProductId) {
        var product = repository.findById(ProductId);

        if (product.isEmpty())
            return new ErrorResult(Messages.product_not_found.toString());

        repository.delete(product.get());

        return new SuccessResult(Messages.success.toString());
    }

    public DataResult<List<ProductDto>> GetList() {
        var products = repository.findAll();

        var ProductList = new ArrayList<ProductDto>();
        for (var product : products) {
            var categoryOfProduct = product.getCategory();

            var productDto = new ProductDto();

            if (categoryOfProduct != null) {
                var categoryDto = new CategoryDto(
                        categoryOfProduct.getId(),
                        categoryOfProduct.getTitle(),
                        categoryOfProduct.getImageURL(),
                        categoryOfProduct.getCreatedDate(),
                        categoryOfProduct.getModifiedDate()
                );

                productDto.setCategory(categoryDto);
            }

            productDto.setId(product.getId());
            productDto.setTitle(product.getTitle());
            productDto.setImageURL(product.getImageURL());
            productDto.setPrice(product.getPrice());
            productDto.setCreatedDate(product.getCreatedDate());
            productDto.setModifiedDate(product.getModifiedDate());

            ProductList.add(productDto);
        }

        return new SuccessDataResult<List<ProductDto>>(ProductList, Messages.success.toString());
    }

    public DataResult<ProductDto> GetById(Integer ProductId) {
        var product = repository.findById(ProductId);

        if (product.isEmpty())
            return new ErrorDataResult<ProductDto>(null, Messages.product_not_found.toString());

        var categoryOfProduct = product.get().getCategory();

        var productDto = new ProductDto();

        if (categoryOfProduct != null) {
            var categoryDto = new CategoryDto(
                    categoryOfProduct.getId(),
                    categoryOfProduct.getTitle(),
                    categoryOfProduct.getImageURL(),
                    categoryOfProduct.getCreatedDate(),
                    categoryOfProduct.getModifiedDate()
            );

            productDto.setCategory(categoryDto);
        }

        productDto.setId(product.get().getId());
        productDto.setTitle(product.get().getTitle());
        productDto.setImageURL(product.get().getImageURL());
        productDto.setPrice(product.get().getPrice());
        productDto.setCreatedDate(product.get().getCreatedDate());
        productDto.setModifiedDate(product.get().getModifiedDate());

        return new SuccessDataResult<ProductDto>(productDto, Messages.success.toString());
    }
}
