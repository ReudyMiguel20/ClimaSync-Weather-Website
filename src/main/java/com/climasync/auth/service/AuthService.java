package com.climasync.auth.service;

import com.climasync.auth.model.dto.AuthenticationRequest;
import com.climasync.auth.model.dto.AuthenticationToken;
import com.climasync.auth.model.dto.AuthenticationTokenWithName;
import com.climasync.user.model.dto.RegisterRequest;

public interface AuthService {

    AuthenticationToken register(RegisterRequest request);

    AuthenticationTokenWithName authenticateUser(AuthenticationRequest authenticationRequest);
}
