package sefakpsz.allInOnce.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sefakpsz.allInOnce.daos.Category.CategoryDao;
import sefakpsz.allInOnce.repositories.CategoryRepository;
import sefakpsz.allInOnce.utils.results.DataResult;
import sefakpsz.allInOnce.utils.results.Result;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public Result Create(CategoryDao dao) {
        return null;
    }

    public Result Update(CategoryDao dao) {
        return null;
    }

    public Result Delete(Integer categoryId) {
        return null;
    }

    public DataResult<List<CategoryDao>> GetList() {
        return null;
    }

    public Result GetById(Integer categoryId) {
        return null;
    }
}
