package com.ensat.controllers;

import com.ensat.entities.Category;
import com.ensat.entities.Product;
import com.ensat.services.CategoryService;
import com.ensat.services.ProductService;
import com.ensat.tos.ProductCategoriesTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Product controller.
 */
@Controller
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public void setProductService(ProductService productService,
                                  CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    /**
     * List all products.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String listAll(Model model) {
        model.addAttribute("products", productService.listAllProducts());
        System.out.println("Returning rpoducts:");
        return "products";
    }

    /**
     * View a specific product by its id.
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("product/{id}")
    public String showProduct(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "productshow";
    }

    // Afficher le formulaire de modification du Product
    @RequestMapping("product/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Iterable<Category> categories = categoryService.listAllCategory();
        model.addAttribute("categories", categories);
        model.addAttribute("product", productService.getProductById(id));
        return "productform";
    }

    /**
     * New product.
     *
     * @param model
     * @return
     */
    @RequestMapping("product/new")
    public String newProduct(Model model) {
        Iterable<Category> categories = categoryService.listAllCategory();
        model.addAttribute("categories", categories);
        model.addAttribute("product", new Product());
        return "productform";
    }

    /**
     * Save product to database.
     *
     * @param product
     * @return
     */
    @RequestMapping(value = "product", method = RequestMethod.POST)
    public String saveProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/product/" + product.getId();
    }

    /**
     * Delete product by its id.
     *
     * @param id
     * @return
     */
    @RequestMapping("product/delete/{id}")
    public String delete(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    /**
     * List all products.
     *
     * @param model
     * @return
     */
    @GetMapping("/rest/v1/products")
    public ResponseEntity<Iterable<Product>> list(Model model) {
        return ResponseEntity.ok()
                .eTag("All Products")
                .body(productService.listAllProducts());
    }

    /**
     * Inser new product REST API
     *
     * @param product
     * @return
     */
    @PostMapping("/rest/v1/product")
    ResponseEntity newProducts(@RequestBody Product product) {
        try {
            productService.saveProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .eTag("New Product")
                    .body("Product inserted");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .eTag("New Product")
                    .body("Product insertion failed");
        }
    }

}
