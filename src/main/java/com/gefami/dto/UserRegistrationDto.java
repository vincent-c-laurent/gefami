package com.gefami.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {
    @Email(regexp = "^[A-Za-z0-9+_.-]+@(gmail\\.com|hotmail\\.com|yahoo\\.com)$", 
           message = "Email must be a valid domain (gmail.com, hotmail.com, etc)")
    private String email;
    
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]{8,}$",
             message = "Password must be at least 8 characters long, contain one uppercase letter and one number")
    private String password;
} 