package com.ensat.repositories;

import com.ensat.entities.Category;
import com.ensat.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
