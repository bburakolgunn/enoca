package com.example.enocaecommerce.ServiceImpl;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.enocaecommerce.Entity.Cart;

public interface CartServiceImpl {
	
	ResponseEntity<Cart> save(Cart cart);
	
	ResponseEntity<Cart> update(Cart cart);
	
	ResponseEntity<Void> delete(Long id);
	
	ResponseEntity<List<Cart>> list();
	
	ResponseEntity<List<Cart>> customerCart(String email);

}
