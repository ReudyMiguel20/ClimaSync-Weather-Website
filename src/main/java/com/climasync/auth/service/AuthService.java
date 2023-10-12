package com.climasync.auth.service;

import com.climasync.auth.model.dto.AuthenticationResponse;
import com.climasync.user.model.dto.RegisterRequest;

public interface AuthService {

    AuthenticationResponse register(RegisterRequest request);
}
