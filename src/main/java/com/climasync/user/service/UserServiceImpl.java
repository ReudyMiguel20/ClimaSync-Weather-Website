package com.climasync.user.service;

import com.climasync.common.dto.StatusMessage;
import com.climasync.user.exception.UserAlreadyExists;
import com.climasync.user.exception.UserNotFound;
import com.climasync.user.model.dto.RegisterRequest;
import com.climasync.user.model.entity.Role;
import com.climasync.user.model.entity.User;
import com.climasync.user.repository.UserRepository;
import com.climasync.weather.model.entity.CurrentWeather;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User retrieveUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFound::new);
    }

    /**
     * Saves the current weather to the user history, if the user history already contains the current weather then it won't be saved.
     *
     * @param user - the user to save the current weather to
     * @param currentWeather - the current weather to save to the user history
     */
    @Override
    public void saveCurrentWeatherToUserHistory(User user, CurrentWeather currentWeather) {
        if (!user.getCurrentWeatherHistory().contains(currentWeather)) {

            // If there's five or more weather history in the stack, then remove the oldest one
            if (user.getCurrentWeatherHistory().size() >= 5) {
                user.getCurrentWeatherHistory().remove(0);
            }

            // Push/Add the new weather history to the stack
            user.getCurrentWeatherHistory().add(currentWeather);
            saveUser(user);
        }
    }

    /**
     * Saves the user to the database and returns a status message confirming the user creation was successful.
     *
     * @param user - the user to save to the database and return a status message for it
     * @return - the status message confirming the user creation was successful
     */
    @Override
    public StatusMessage saveUserAndReturnStatusMessage(User user) {
        if (doUserAlreadyExists(user)) {
            throw new UserAlreadyExists();
        } else {
            saveUser(user);
        }

        return StatusMessage.builder()
                .message("User created successfully")
                .build();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Method that uses ModelMapper is used to map the RegisterRequest object to a User object, after that it encodes the password and assigns
     * a role to the user before saving it to the database and returning a status message to the controller confirming the user creation was successful.
     *
     * @param registerRequest - the register request object that contains the user information to be mapped to a User object.
     * @return - the status message confirming the user creation was successful
     */
    @Override
    public User createNewUserAndAssignRole(RegisterRequest registerRequest) {
        User user = modelMapper.map(registerRequest, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        assignRoleToUser(user);
        saveUser(user);

        return user;
    }

    /**
     * Assign the role to the user, if there's no user in the database then the first user will be an 'Admin', any other user
     * registered after that is going to be assigned with a 'User' role
     *
     * @param user - the user to assign the role to
     */
    @Override
    public void assignRoleToUser(User user) {
        if (getAllUsers().isEmpty()) {
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }
    }

    @Override
    public boolean doUserAlreadyExists(User user) {
        return userRepository.findByEmail(user.getEmail()).isPresent();
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

}
