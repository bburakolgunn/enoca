package com.example.enocaecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.enocaecommerce.Entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {

}
