package com.example.enocaecommerce.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper=false)
public class Cart extends BaseEntity {
	
	  	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "product_id")
	    private Product product;

	    @ManyToOne
	    @JoinColumn(name = "customer_Id")
	    private Customer customer;
	    
	    @ManyToOne
	    private Orders order;

	    boolean status= false;

	    private int quantity;
	}





