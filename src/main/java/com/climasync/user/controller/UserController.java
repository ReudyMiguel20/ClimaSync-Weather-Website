package com.climasync.user.controller;

import com.climasync.common.dto.StatusMessage;
import com.climasync.user.model.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    @PostMapping
    public ResponseEntity<StatusMessage> createNewUser(@Valid @RequestBody RegisterRequest request) {
        
    }
}
