package com.example.enocaecommerce.ServiceImpl;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.enocaecommerce.Entity.Orders;

public interface OrderServiceImpl {
	
	ResponseEntity<Orders> save(Orders orders);
	
	ResponseEntity<Orders> update(Orders orders);
	
	 ResponseEntity<Void> delete(Long id);
	
	 ResponseEntity<List<Orders>> list();

}
