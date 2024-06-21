package com.Almaamouny.ProductService.Product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductService {

    private ProductRepository productRepository;
    private ProductMapper productMapper;

    public void createProduct(ProductRequest request){

        Product product=productMapper.toProduct(request);

        productRepository.save(product);
    }
    public void deleteProduct(Integer id){


        productRepository.deleteById(id);
    }

    public void updateProduct(ProductRequest request){

        Product product=productMapper.toProduct(request);

        product=productRepository.findById(product.getId()).get();

        productMapper.toProduct(product,request);

        productRepository.save(product);


    }

    public ProductResponse getProduct(Integer id){

        Product product=productRepository.findById(id).
                orElseThrow(() -> new ProductNotFoundException(String.format("No Product found with the provided ID: %d", id))
        );

        return productMapper.fromProduct(product);
    }

    public List<ProductResponse> getAllProduct(){

        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(product -> productMapper.fromProduct(product))
                .collect(Collectors.toList());
    }



    @Transactional(rollbackFor = ProductNotFoundException.class)
    public List<PurchaseResponse> purchaseProducts(
            List<PurchaseRequest> request
    ) {
        List<Integer> productIds = request
                .stream()
                .map(PurchaseRequest::id)
                .toList();
        List<Product> storedProducts = productRepository.findAllById(productIds);

        var sortedRequest = request
                .stream()
                .sorted(Comparator.comparing(PurchaseRequest::id))
                .toList();

        List<PurchaseResponse> purchasedProducts = new ArrayList<PurchaseResponse>();
        for (int i = 0; i < storedProducts.size(); i++) {
            Product product = storedProducts.get(i);
            PurchaseRequest productRequest = sortedRequest.get(i);
            if (product.getQuantity() < productRequest.quantity()) {
                throw new ProductNotFoundException("Insufficient stock quantity for product with ID:: " + productRequest.id());
            }
            Integer newAvailableQuantity = product.getQuantity() - productRequest.quantity();
            product.setQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(productMapper.toPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }

}
