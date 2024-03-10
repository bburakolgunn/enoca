package com.example.enocaecommerce.ServiceImpl;

import com.example.enocaecommerce.Entity.Customer;
import com.example.enocaecommerce.Entity.Product;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface CustomerServiceImpl {

    ResponseEntity<Customer> save(Customer customer);

    ResponseEntity<Customer> update(Customer customer);

    ResponseEntity<Void> delete(Long id);

    ResponseEntity<List<Customer>> list();
    
 

}