package com.example.enocaecommerce.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.enocaecommerce.Entity.Product;
import com.example.enocaecommerce.Repository.ProductRepository;
import com.example.enocaecommerce.ServiceImpl.ProductServiceImpl;

@Service
public class ProductService implements ProductServiceImpl {
	
	private ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@Override
	public ResponseEntity<Product> save(Product product) {
		   try {
	            productRepository.save(product);
	            return new ResponseEntity<>(product, HttpStatus.OK);
	        } catch (Exception ex) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	    }

	@Override
	public ResponseEntity<Product> update(Product product) {
		 try {
	            Optional<Product> optionalProduct = productRepository.findById(product.getId());
	            if (optionalProduct.isPresent()) {
	                productRepository.saveAndFlush(product);
	                return new ResponseEntity<>(product, HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	            }
	        } catch (Exception ex) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	    }
	
	

	@Override
	public ResponseEntity<Void> delete(Long id) {
		try {
            productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

	@Override
	public ResponseEntity<List<Product>> list() {
		List<Product> products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

	@Override
	public ResponseEntity<List<Product>> search(String q) {
	    List<Product> productList = productRepository.findByProductNameContainsIgnoreCaseOrDetailContainsIgnoreCase(q, q);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
}
