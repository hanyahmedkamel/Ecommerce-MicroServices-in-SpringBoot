package com.Almaamouny.ProductService.Product;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController{

    private ProductService productService;

    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping("/create_product")
    public void createProduct(@RequestBody ProductRequest request){

        productService.createProduct(request);

    }

    @PutMapping("/product")
    @PreAuthorize("hasRole('ROLE_admin')")
    public void updateProduct(@RequestBody ProductRequest request){

        productService.updateProduct(request);

    }

    @DeleteMapping("/product/{id}")
    @PreAuthorize("hasRole('ROLE_admin')")
    public void deleteProduct(@PathVariable Integer id){

        productService.deleteProduct(id);


    }

    @GetMapping("/product/{id}")
    public ProductResponse getProduct(@PathVariable Integer id){

        return productService.getProduct(id);
    }

    @GetMapping("/products")
    public List<ProductResponse> getAllProducts(){


        return productService.getAllProduct();

    }

    @PostMapping("/product")
    public List<PurchaseResponse> purchaseRequest (@RequestBody List<PurchaseRequest> requests){
        System.out.println("HELLO");

        System.out.println(requests.get(0).id());
        System.out.println(requests.get(0).quantity());
        List<PurchaseResponse> responses=null;
        try {
             responses=productService.purchaseProducts(requests);
        }catch (ProductNotFoundException productNotFoundException){

            System.out.println(productNotFoundException.getMsg());
        }



     return  responses;



    }

}
