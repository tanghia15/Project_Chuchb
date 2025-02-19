package com.hc.authentication.controller;

import com.hc.authentication.dto.input.LoginRequest;
import com.hc.authentication.entity.User;
import com.hc.authentication.exception.UserAlreadyExistsException;
import com.hc.authentication.service.IAuthService;
import com.hc.authentication.service.IUserService;
import com.hc.authentication.dto.output.JwtResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IUserService userService;
    private final IAuthService authService;

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, @RequestParam String role){
        try{
            userService.registerUser(user, role);
            return ResponseEntity.ok("Registration successful!");

        }catch (UserAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request) {
        JwtResponse jwtResponse = authService.authenticateUser(request);
        return ResponseEntity.ok(jwtResponse);
    }
}
