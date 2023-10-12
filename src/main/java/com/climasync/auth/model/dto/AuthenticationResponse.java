package com.climasync.auth.model.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.RestController;

@Data
@Builder
public class AuthenticationResponse {
    private String token;
}
