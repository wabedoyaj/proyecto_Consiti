package com.eccomerce_wilson.eccomerce.Service;

import com.eccomerce_wilson.eccomerce.DTO.ProductDTO;
import com.eccomerce_wilson.eccomerce.Entity.Category;
import com.eccomerce_wilson.eccomerce.Entity.Product;

import java.util.List;

public interface ICategoryService {
    Category getCategoryById(Long categoryId);
    List<Category> getAllCategory();
    Category createCategory(Category category);
    Category updateCategory(Long categoryId, Category category);
    void deleteCategory(Long categoryId);
}
