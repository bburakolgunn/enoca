package com.example.enocaecommerce.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.enocaecommerce.Entity.Cart;
import com.example.enocaecommerce.Entity.Customer;
import com.example.enocaecommerce.Service.CartService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	
	private CartService cartService;
	private HttpSession httpSession;
	

	public CartController(CartService cartService, HttpSession httpSession) {
		super();
		this.cartService = cartService;
		this.httpSession = httpSession;
	}

	@PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody Cart cart){
        return cartService.save(cart);
    }

    @GetMapping("/list")
    public ResponseEntity list(){
        return cartService.list();
    }

    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody Cart cart){
        return cartService.update(cart);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestParam Long id){
         return  cartService.delete(id);
    }

    @GetMapping("/MyOrders")
    public ResponseEntity MyOrders(){
    	Customer customer = (Customer) httpSession.getAttribute("customer");
        if (customer != null) {
            return cartService.customerCart(customer.getEmail());
        } else {
            // Müşteri bilgisi bulunamadığında ne yapılacağını belirleyin.
            // Örneğin, bir hata mesajı döndürebilir veya uygun bir HTTP durum koduyla hata döndürebilirsiniz.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer information not found.");
        }

}
}