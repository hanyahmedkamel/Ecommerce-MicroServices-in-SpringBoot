package com.Almaamouny.ProductService.Product;

import com.Almaamouny.ProductService.Product.Product;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Product> findAllById(Iterable<Integer> ids);

}
