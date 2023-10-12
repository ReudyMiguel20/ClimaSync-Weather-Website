package com.climasync.user.controller;

import com.climasync.common.dto.StatusMessage;
import com.climasync.user.model.dto.RegisterRequest;
import com.climasync.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;


//    @PostMapping("/register")
//    public ResponseEntity<StatusMessage> createNewUser(@Valid @RequestBody RegisterRequest registerRequest) {
//        StatusMessage statusMessage = userService.createNewUserAndAssignRole(registerRequest);
//
//        return ResponseEntity.ok(statusMessage);
//    }


}
