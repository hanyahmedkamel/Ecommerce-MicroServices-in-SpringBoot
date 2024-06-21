package com.Almaamouny.ProductService.Product;

import com.Almaamouny.ProductService.Category.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductMapper {

    private CategoryRepository categoryRepository;

    public Product toProduct(ProductRequest request){

     return    Product.builder().
                name(request.name()).
                category(categoryRepository.findById(request.category()).get()).
                description(request.description()).
                id(request.id()).
                quantity(request.quantity()).build();

    }

    public void toProduct(Product product,ProductRequest request){
        if (request.category()!=null)
            product.setCategory(categoryRepository.findById(request.category()).get());
        if (request.name()!=null)
            product.setName(request.name());
        if (request.description()!=null)
            product.setDescription(request.description());
        if (request.quantity()!=null)
            product.setQuantity(request.quantity());
        if (request.price()!=null)
            product.setPrice(request.price());

    }

    public ProductResponse fromProduct(Product product){

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory().getId()

        );
    }

    public PurchaseResponse toPurchaseResponse(Product product, Integer quantity) {
        return new PurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
