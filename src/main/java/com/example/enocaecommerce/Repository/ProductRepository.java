package com.example.enocaecommerce.Repository;

import com.example.enocaecommerce.Entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

	List<Product> findByProductNameContainsIgnoreCaseOrDetailContainsIgnoreCase(String productName, String detail);

}
