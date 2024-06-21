package com.Almaamouny.ProductService.Category;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping("/Category")
    public void createCategory(@RequestBody CategoryRequest categoryRequest){

        categoryService.createCategory(categoryRequest);
    }

    @GetMapping("/Categories")
    public List<CategoryResponse> getAllCategories(){

       return categoryService.getAllCategories();

    }

    @GetMapping("/Category/{id}")
    public CategoryResponse getCategory(@PathVariable Integer id){

       return categoryService.getCategory(id);

    }

    @PutMapping("/Category")
    public void updateCategory(@RequestBody  CategoryRequest categoryRequest){

        categoryService.updateCategory(categoryRequest);
    }
}
