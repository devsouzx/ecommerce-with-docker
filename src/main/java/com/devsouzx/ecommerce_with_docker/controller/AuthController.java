package com.devsouzx.ecommerce_with_docker.controller;

import com.devsouzx.ecommerce_with_docker.dto.ChangePasswordRequest;
import com.devsouzx.ecommerce_with_docker.dto.EmailConfirmationRequest;
import com.devsouzx.ecommerce_with_docker.dto.LoginRequest;
import com.devsouzx.ecommerce_with_docker.exception.ResourceNotFoundException;
import com.devsouzx.ecommerce_with_docker.model.User;
import com.devsouzx.ecommerce_with_docker.service.JwtService;
import com.devsouzx.ecommerce_with_docker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        final UserDetails userDetails = userService.getUserByEmail(request.getEmail());
        final String jwt = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody User user){
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/confirm-email")
    public ResponseEntity<?> confirmEmail(@RequestBody EmailConfirmationRequest request) {
        try {
            userService.confirmEmail(request.getEmail(), request.getConfirmationCode());
            return ResponseEntity.ok().body("Email confirmed successfuly");
        } catch (BadCredentialsException e){
            return ResponseEntity.badRequest().body("Invalid confirmation code");
        } catch (ResourceNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        userService.changePassword(email, request);
        return ResponseEntity.ok().body("Password changed");
    }
}
