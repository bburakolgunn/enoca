package com.example.enocaecommerce.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enocaecommerce.Entity.Product;
import com.example.enocaecommerce.Service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {
    final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody Product product){
        return productService.save(product);
    }


    @GetMapping("/list")
    public ResponseEntity list(){
        return productService.list();
    }

    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody Product product){
        return productService.update(product);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        return productService.delete(id);
    }

}

