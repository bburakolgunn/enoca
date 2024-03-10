package com.example.enocaecommerce.ServiceImpl;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.enocaecommerce.Entity.Product;

public interface ProductServiceImpl {
	
	ResponseEntity<Product> save(Product product);
	
	ResponseEntity<Product> update(Product product);
	
	ResponseEntity<Void> delete(Long id);
	
	ResponseEntity<List<Product>> list();
	
	ResponseEntity<List<Product>> search(String q);

}
	