package com.example.enocaecommerce.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.validator.constraints.Length;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Firstname can't be blank")
    @Length(message = "Firstname must be between 3 and 50 characters", min = 3, max = 50)
    private String firstName;


    @NotBlank(message = "Lastname can't be blank")
    @Length(message = "Lastname must be between 3 and 50 characters", min = 3, max = 50)
    private String lastName;


    @NotBlank(message = "Phone can't be blank")
    private String phone;
    
    
    
    @Email(message = "Email should be valid")
    private String email;
}

