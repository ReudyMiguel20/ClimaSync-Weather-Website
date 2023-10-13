package com.climasync.auth.service.impl;

import com.climasync.auth.model.dto.AuthenticationRequest;
import com.climasync.auth.model.dto.AuthenticationToken;
import com.climasync.auth.model.dto.AuthenticationTokenWithName;
import com.climasync.auth.service.AuthService;
import com.climasync.common.jwt.JwtService;
import com.climasync.user.model.dto.RegisterRequest;
import com.climasync.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import com.climasync.user.service.UserService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationToken register(RegisterRequest request) {
        User newUser = userService.createNewUserAndAssignRole(request);

        String jwtToken = jwtService.generateToken(newUser);

        return AuthenticationToken.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationTokenWithName authenticateUser(AuthenticationRequest authenticationRequest) {

        // Get the user for token generation and firstName for response return
        User user = userService.retrieveUserByEmail(authenticationRequest.getEmail());
        String firstName = user.getFirstName();

        // Authenticate the user and generate new jwt token
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(), authenticationRequest.getPassword())
        );

        String jwtToken = jwtService.generateToken(user);

        // Return AuthenticationTokenWithName object with jwt token and firstName
        return AuthenticationTokenWithName.builder()
                .token(jwtToken)
                .firstName(firstName)
                .build();

    }
}
