package sefakpsz.allInOnce.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sefakpsz.allInOnce.dtos.Category.CategoryCreateDto;
import sefakpsz.allInOnce.dtos.Category.CategoryDto;
import sefakpsz.allInOnce.dtos.Category.CategoryUpdateDto;
import sefakpsz.allInOnce.entities.Category;
import sefakpsz.allInOnce.repositories.CategoryRepository;
import sefakpsz.allInOnce.repositories.ProductRepository;
import sefakpsz.allInOnce.utils.constants.Messages;
import sefakpsz.allInOnce.utils.results.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    private final ProductRepository productRepository;

    public Result Create(CategoryCreateDto Dto) {
        var category = new Category();

        category.setTitle(Dto.getTitle());
        category.setImageURL(Dto.getImageURL());

        var titleExists = repository.findCategoryByTitle(category.getTitle());

        if (titleExists != null)
            return new ErrorResult(Messages.category_already_exists.toString());

        repository.save(category);

        return new SuccessResult(Messages.success.toString());
    }

    public Result Update(CategoryUpdateDto Dto) {
        var category = repository.findById(Dto.getCategoryId());

        if (category.isEmpty())
            return new ErrorResult(Messages.category_not_found.toString());

        var titleExists = repository.findCategoryByTitle(Dto.getTitle());

        if (titleExists != null)
            return new ErrorResult(Messages.category_already_exists.toString());

        category.get().setTitle(Dto.getTitle());
        category.get().setImageURL(Dto.getImageURL());
        category.get().setModifiedDate(LocalDateTime.now());

        repository.save(category.get());

        return new SuccessResult(Messages.success.toString());
    }

    public Result Delete(Integer categoryId) {
        var category = repository.findById(categoryId);

        if (category.isEmpty())
            return new ErrorResult(Messages.category_not_found.toString());

        var productsOfTheCategory = productRepository.findProductsByCategory(category.get());

        for (var product : productsOfTheCategory) {
            product.setCategory(null);

            productRepository.save(product);
        }

        repository.delete(category.get());

        return new SuccessResult(Messages.success.toString());
    }

    public DataResult<List<CategoryDto>> GetList() {
        var categories = repository.findAll();

        var categoryList = new ArrayList<CategoryDto>();
        for (var category : categories) {
            var categoryDto = new CategoryDto();

            categoryDto.setId(category.getId());
            categoryDto.setTitle(category.getTitle());
            categoryDto.setImageURL(category.getImageURL());
            categoryDto.setCreatedDate(category.getCreatedDate());
            categoryDto.setModifiedDate(category.getModifiedDate());

            categoryList.add(categoryDto);
        }

        return new SuccessDataResult<List<CategoryDto>>(categoryList, Messages.success.toString());
    }

    public DataResult<CategoryDto> GetById(Integer categoryId) {
        var category = repository.findById(categoryId);

        if (category.isEmpty())
            return new ErrorDataResult<CategoryDto>(null, Messages.category_not_found.toString());

        var categoryDto = new CategoryDto();

        categoryDto.setModifiedDate(category.get().getModifiedDate());
        categoryDto.setCreatedDate(category.get().getCreatedDate());
        categoryDto.setTitle(category.get().getTitle());
        categoryDto.setImageURL(category.get().getTitle());
        categoryDto.setId(category.get().getId());

        return new SuccessDataResult<CategoryDto>(categoryDto, Messages.success.toString());
    }
}
