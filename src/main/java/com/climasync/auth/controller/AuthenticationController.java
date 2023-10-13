package com.climasync.auth.controller;

import com.climasync.auth.model.dto.AuthenticationRequest;
import com.climasync.auth.model.dto.AuthenticationToken;
import com.climasync.auth.model.dto.AuthenticationTokenWithName;
import com.climasync.auth.service.AuthService;
import com.climasync.user.model.dto.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationToken> registeringNewUser(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationTokenWithName> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authService.authenticateUser(authenticationRequest));
    }
}
