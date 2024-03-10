package com.example.enocaecommerce.Controller;

import com.example.enocaecommerce.Entity.Customer;
import com.example.enocaecommerce.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register") 
    public ResponseEntity register(@Valid @RequestBody Customer customer){
        return customerService.save(customer);
    }


    @GetMapping("/list")
    public ResponseEntity list(){
        return customerService.list();
    }

    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody Customer customer){
        return customerService.update(customer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        return  customerService.delete(id);
    }
}



