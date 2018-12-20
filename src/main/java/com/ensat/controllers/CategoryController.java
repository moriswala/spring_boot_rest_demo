package com.ensat.controllers;

import com.ensat.entities.Category;
import com.ensat.entities.Product;
import com.ensat.services.CategoryService;
import com.ensat.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Product controller.
 */
@Controller
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * List all categories.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String listAll(Model model) {
        model.addAttribute("categories", categoryService.listAllCategory());
        System.out.println("Returning categories:");
        return "categories";
    }

    /**
     * View a specific product by its id.
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("category/{id}")
    public String showProduct(@PathVariable Integer id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "categoryshow";
    }

    // Afficher le formulaire de modification du Product
    @RequestMapping("category/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "categoryform";
    }

    /**
     * New product.
     *
     * @param model
     * @return
     */
    @RequestMapping("category/newcategory")
    public String newProduct(Model model) {
        model.addAttribute("category", new Category());
        return "categoryform";
    }

    /**
     * Save product to database.
     *
     * @param category
     * @return
     */
    @RequestMapping(value = "category", method = RequestMethod.POST)
    public String saveProduct(Category category) {
        categoryService.saveCategory(category);
        return "redirect:/category/" + category.getId();
    }

    /**
     * Delete product by its id.
     *
     * @param id
     * @return
     */
    @RequestMapping("category/delete/{id}")
    public String delete(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }

    /**
     * List all products.
     *
     * @param model
     * @return
     */
    @GetMapping("/rest/v1/categories")
    public ResponseEntity<Iterable<Category>> list(Model model) {
        return ResponseEntity.ok()
                .eTag("All Categories")
                .body(categoryService.listAllCategory());
    }
}
