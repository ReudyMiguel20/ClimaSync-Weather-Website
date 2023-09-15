package com.climasync.user.service;

import com.climasync.common.dto.StatusMessage;
import com.climasync.user.model.dto.RegisterRequest;
import com.climasync.user.model.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    StatusMessage saveUserAndReturnStatusMessage(User user);

    List<User> getAllUsers();

    StatusMessage createNewUser(RegisterRequest registerRequest);
}
