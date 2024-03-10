package com.example.enocaecommerce.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product Name can't be blank")
    @Length(message = "Product Name must contain min 2 max  50 character.", min = 2, max = 50)
    private String productName;

    @NotBlank(message = "Detail can't be blank")
    @Length(message = "Company name must contain min 2 max  50 character.", min = 2, max = 50)
    private String detail;

    @Range(message = "price can be between 0 and 99999", min = 0, max = 99999)
    private int price;


    @PositiveOrZero
    @Column(nullable = false)
    private int Stock;


}
