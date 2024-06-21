package com.Almaamouny.ProductService.Category;

import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {


    public Category toCategory(CategoryRequest request){
        if (request == null) {
            return null;
        }


        return Category.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .build();


    }

    public void toCategory(Category category,CategoryRequest request){
        if (request == null) {
            return ;
        }


        if (request.description()!=null)
            category.setDescription(request.description());
        if (request.name()!=null)
            category.setName(request.name());


    }




    public CategoryResponse fromCategory(Category category){

        if (category == null) {
            return null;
        }

        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription()
        );

    }

}
