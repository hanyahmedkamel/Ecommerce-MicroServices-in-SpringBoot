package com.Almaamouny.ProductService.Category;

import com.Almaamouny.ProductService.Category.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class CategoryService {

    private CategoryRepository categoryRepository;

    private CategoryMapper categoryMapper;

    public void createCategory(CategoryRequest categoryRequest){

        categoryRepository.save(categoryMapper.toCategory(categoryRequest));

    }

    public CategoryResponse getCategory(Integer categoryId){

        Category category= categoryRepository.findById(categoryId).orElseThrow(()->new CategoryNotFoundException(
                String.format("Cannot update customer:: No Category found with the provided ID: %d", categoryId)
        ));

        return categoryMapper.fromCategory(category);
    }

    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(category -> categoryMapper.fromCategory(category))
                .collect(Collectors.toList());
    }

    public void updateCategory(CategoryRequest categoryRequest) {

       Optional<Category> tempCategory = categoryRepository.findById(categoryRequest.id());

       Category category=tempCategory.get();

       categoryMapper.toCategory(category,categoryRequest);

       categoryRepository.save(category);

    }


}
