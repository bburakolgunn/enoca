package com.example.enocaecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.enocaecommerce.Entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	
	List<Cart> findByCustomer_EmailEqualsIgnoreCaseAndStatusFalse(String email);

    List<Cart> findByStatusIsTrueAndCustomer_EmailEqualsIgnoreCase(String email);
    
   
}
