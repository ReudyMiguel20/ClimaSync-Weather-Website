package com.climasync.user.controller;

import com.climasync.common.dto.StatusMessage;
import com.climasync.user.model.dto.RegisterRequest;
import com.climasync.user.service.UserService;
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

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<StatusMessage> createNewUser(@Valid @RequestBody RegisterRequest registerRequest) {
        StatusMessage statusMessage = userService.createNewUser(registerRequest);

        return ResponseEntity.ok(statusMessage);
    }


}
