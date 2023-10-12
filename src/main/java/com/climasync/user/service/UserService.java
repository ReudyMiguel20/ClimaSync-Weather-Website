package com.climasync.user.service;

import com.climasync.common.dto.StatusMessage;
import com.climasync.user.model.dto.RegisterRequest;
import com.climasync.user.model.entity.User;
import com.climasync.weather.model.entity.CurrentWeather;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    User retrieveUserByEmail(String email);

    void saveCurrentWeatherToUserHistory(User user, CurrentWeather currentWeather);

    StatusMessage saveUserAndReturnStatusMessage(User user);

    List<User> getAllUsers();

    User createNewUserAndAssignRole(RegisterRequest registerRequest);

    void assignRoleToUser(User user);

    boolean doUserAlreadyExists(User user);

    void deleteAll();
}
