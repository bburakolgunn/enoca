package com.example.enocaecommerce.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.enocaecommerce.Entity.Cart;
import com.example.enocaecommerce.Entity.Customer;
import com.example.enocaecommerce.Entity.Product;
import com.example.enocaecommerce.Repository.CartRepository;
import com.example.enocaecommerce.Repository.CustomerRepository;
import com.example.enocaecommerce.Repository.ProductRepository;
import com.example.enocaecommerce.ServiceImpl.CartServiceImpl;

import jakarta.servlet.http.HttpSession;

@Service
public class CartService implements CartServiceImpl {

	private CartRepository cartRepository;
	private ProductRepository productRepository;
	private HttpSession httpSession;
	private CustomerRepository customerRepository;
	
	
	public CartService(CartRepository cartRepository, ProductRepository productRepository, HttpSession httpSession,CustomerRepository customerRepository) {
		super();
		this.cartRepository = cartRepository;
		this.productRepository = productRepository;
		this.httpSession = httpSession;
		this.customerRepository = customerRepository;
	}

	@Override
	public ResponseEntity save(Cart cart) {
		cart.getCustomer().getId();
		Optional<Customer> customer = customerRepository.findById(cart.getCustomer().getId());
		List<Cart> carts = cartRepository.findByStatusIsTrueAndCustomer_EmailEqualsIgnoreCase(customer.get().getEmail());
        boolean isSameProduct = false;
        Long cartId = 0L;
        int oldQuantityBasket = 0;

        Optional<Product> optionalProduct = productRepository.findById(cart.getProduct().getId());

        if (optionalProduct.isPresent()) {
            for(Cart cartItem : carts) {
                if (cartItem.getProduct().getId().equals(cart.getProduct().getId())) {
                    isSameProduct = true;
                    cartId = cartItem.getId();
                    oldQuantityBasket = cartItem.getQuantity();
                    break;
                }
            }
            Product product = optionalProduct.get();
            Integer stockQuantity = product.getStock();
            Integer basketQuantity = cart.getQuantity();

            if (basketQuantity <= stockQuantity) {
                product.setStock(stockQuantity - basketQuantity);
                productRepository.save(product);
                cart.setProduct(product);
                if (isSameProduct) {
                    cart.setId(cartId);
                    cart.setQuantity(basketQuantity + oldQuantityBasket);
                    cart.setCreatedBy(customer.get().getEmail());
                }
                cartRepository.save(cart);
                return new ResponseEntity<>(cart, HttpStatus.OK);
                 
            } else {
            	return new ResponseEntity<>("Not enough stock", HttpStatus.BAD_REQUEST);
            	
            }

        } else {
        	return new ResponseEntity<>("There is not such a product", HttpStatus.BAD_REQUEST);
        }
	}

	@Override
	public ResponseEntity<Cart> update(Cart cart) {
		  try {
	            Optional<Cart> optionalBasket = cartRepository.findById(cart.getId());
	            if (optionalBasket.isPresent()) {
	                cartRepository.saveAndFlush(cart);
	                return new ResponseEntity<>(cart, HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	                //return new ResponseEntity<>("Cart is null! try again", HttpStatus.BAD_REQUEST);
	            }
	        } catch (Exception ex) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	}

	@Override
	public ResponseEntity<Void> delete(Long id) {
		try {
            cartRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            //return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
	}

	@Override
	public ResponseEntity<List<Cart>> list() {
		return new ResponseEntity<>(cartRepository.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Cart>> customerCart(String email) {
		List<Cart> carts = cartRepository.findByStatusIsTrueAndCustomer_EmailEqualsIgnoreCase(email);
        return new ResponseEntity<>(carts, HttpStatus.OK);
	}



	

}
