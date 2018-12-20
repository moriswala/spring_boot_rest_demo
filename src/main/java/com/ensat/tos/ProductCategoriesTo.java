package com.ensat.tos;

import com.ensat.entities.Category;
import com.ensat.entities.Product;

public class ProductCategoriesTo {
    private Product product;
    private Category productCategory;
    private Iterable<Category> categories;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Category getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }

    public Iterable<Category> getCategories() {
        return categories;
    }

    public void setCategories(Iterable<Category> categories) {
        this.categories = categories;
    }
}
