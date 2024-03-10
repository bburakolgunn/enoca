package com.example.enocaecommerce.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.enocaecommerce.Entity.Cart;
import com.example.enocaecommerce.Entity.Customer;
import com.example.enocaecommerce.Entity.Orders;
import com.example.enocaecommerce.Repository.CartRepository;
import com.example.enocaecommerce.Repository.CustomerRepository;
import com.example.enocaecommerce.Repository.OrderRepository;
import com.example.enocaecommerce.ServiceImpl.OrderServiceImpl;

@Service
public class OrderService implements OrderServiceImpl {

	private OrderRepository orderRepository;
	private CartRepository cartRepository;
	private CustomerRepository customerRepository;
	
	public OrderService(OrderRepository orderRepository, CartRepository cartRepository, CustomerRepository customerRepository) {
		super();
		this.orderRepository = orderRepository;
		this.cartRepository = cartRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	public ResponseEntity<Orders> save(Orders orders) {
	    int sum = 0;
	    Optional<Customer> customerOptional = customerRepository.findByEmail(orders.getCustomer().getEmail());
	    if (customerOptional.isPresent()) {
	        Customer customer = customerOptional.get();
	        List<Cart> carts = cartRepository.findByCustomer_EmailEqualsIgnoreCaseAndStatusFalse(customer.getEmail());
	        if (!carts.isEmpty()) {
	            for (Cart item : carts) {
	                sum += item.getProduct().getPrice() * item.getQuantity();
	                item.setStatus(true);
	                cartRepository.saveAndFlush(item);
	            }
	            orders.setCarts(carts);
	            orders.setTotal(sum);
	            orderRepository.save(orders);
	            return ResponseEntity.ok(orders);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
	        }
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Müşteri bilgisi bulunamadı
	    }
	}

	@Override
	public ResponseEntity<Orders> update(Orders orders) {
		 try {
	            Optional<Orders> optionalOrders = orderRepository.findById(orders.getId());
	            if (optionalOrders.isPresent()) {
	                orderRepository.saveAndFlush(orders);
	                return ResponseEntity.ok(orders);
	            } else {
	                return ResponseEntity.badRequest().body(null);
	            }
	        } catch (Exception ex) {
	            return ResponseEntity.badRequest().body(null);
	        }
	    }

	@Override
	public ResponseEntity<Void> delete(Long id) {
		 try {
	            orderRepository.deleteById(id);
	            return ResponseEntity.ok().build();
	        } catch (Exception ex) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	        }
	    }

	@Override
	public ResponseEntity<List<Orders>> list() {
		 List<Orders> ordersList = orderRepository.findAll();
	        return ResponseEntity.ok(ordersList);
	    }

}


