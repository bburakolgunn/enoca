package com.example.enocaecommerce.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.example.enocaecommerce.Entity.Orders;
import com.example.enocaecommerce.Service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	private OrderService orderService;

	public OrderController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}
	
	@PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody Orders orders){
        return orderService.save(orders);
    }

    
    @GetMapping("/list")
    public ResponseEntity list(){
        return orderService.list();
    }

    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody Orders orders){
        return orderService.update(orders);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(Long id){
        return  orderService.delete(id);
    }

}
