package com.eccomerce_wilson.eccomerce.Service.implem;

import com.eccomerce_wilson.eccomerce.Entity.Category;
import com.eccomerce_wilson.eccomerce.Exceptions.CategoryExistsException;
import com.eccomerce_wilson.eccomerce.Exceptions.NotFound.CategoryNotFoundException;
import com.eccomerce_wilson.eccomerce.Repository.ICategoryRepository;
import com.eccomerce_wilson.eccomerce.Service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository iCategoryRepository;

    @Override
    public List<Category> getAllCategory() {
        return iCategoryRepository.findAll();
    }
    @Override
    public Category getCategoryById(Long categoryId) {
        return iCategoryRepository.findById(categoryId).orElseThrow(()-> new CategoryNotFoundException(categoryId));
    }

    @Override
    public Category createCategory(Category category) {
        if (iCategoryRepository.existsById(category.getCategoryId())) {
            throw new CategoryExistsException(category.getCategoryId());
        }
        // Crear una nueva instancia de categoria y asignar los valores
        Category category1 = new Category();
        category1.setCategoryId(category.getCategoryId());
        category1.setCategoryName(category.getCategoryName());
         // Guardar el producto en la base de datos
        return iCategoryRepository.save(category1);
    }

    @Override
    public Category updateCategory(Long categoryId, Category category) {
        return iCategoryRepository.findById(categoryId).map( existingCategory -> {

            existingCategory.setCategoryId(category.getCategoryId());
            existingCategory.setCategoryName(category.getCategoryName());
            // Guardar el producto en la base de datos
            return iCategoryRepository.save(existingCategory);
        }).orElseThrow(() -> new CategoryNotFoundException(categoryId));

    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = iCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        iCategoryRepository.delete(category);

    }
}
