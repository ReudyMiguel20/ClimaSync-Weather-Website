package com.climasync.auth.service.impl;

import com.climasync.auth.model.dto.AuthenticationResponse;
import com.climasync.auth.service.AuthService;
import com.climasync.common.jwt.JwtService;
import com.climasync.user.model.dto.RegisterRequest;
import com.climasync.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.climasync.user.service.UserService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        User newUser = userService.createNewUserAndAssignRole(request);

        String jwtToken = jwtService.generateToken(newUser);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
