package com.gefami.controller;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gefami.dto.LoginDto;
import com.gefami.dto.UserRegistrationDto;
import com.gefami.model.User;
import com.gefami.security.JwtUtil;
import com.gefami.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegistrationDto registrationDto) {
        log.info("Received registration request for email: {}", registrationDto.getEmail());
        try {
            User user = userService.registerUser(registrationDto);
            log.info("Successfully registered user with email: {}", user.getEmail());
            return ResponseEntity.ok(new HashMap<String, Object>() {{
                put("message", "User registered successfully");
                put("userId", user.getId());
                put("email", user.getEmail());
            }});
        } catch (Exception e) {
            log.error("Registration failed: ", e);
            return ResponseEntity.badRequest().body(new HashMap<String, String>() {{
                put("error", e.getMessage());
            }});
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(),
                loginDto.getPassword()
            )
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(authentication);
        
        return ResponseEntity.ok(new HashMap<String, String>() {{
            put("token", jwt);
            put("message", "Login successful");
        }});
    }
} 