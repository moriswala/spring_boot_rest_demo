package com.ensat.services;

import com.ensat.entities.Category;
import com.ensat.entities.Product;

public interface CategoryService {

    Iterable<Category> listAllCategory();

    Category getCategoryById(Integer id);

    Category saveCategory(Category category);

    void deleteCategory(Integer id);

}
