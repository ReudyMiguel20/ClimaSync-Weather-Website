package com.climasync.user.service;

import com.climasync.common.dto.StatusMessage;
import com.climasync.user.model.dto.RegisterRequest;
import com.climasync.user.model.entity.Role;
import com.climasync.user.model.entity.User;
import com.climasync.user.repository.UserRepository;
import jdk.jshell.Snippet;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(User user) {

    }

    @Override
    public StatusMessage saveUserAndReturnStatusMessage(User user) {
        userRepository.save(user);

        return StatusMessage.builder()
                .message("User created successfully")
                .build();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public StatusMessage createNewUser(RegisterRequest registerRequest) {
        User user = modelMapper.map(registerRequest, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        assignRoleToUser(user);

        return saveUserAndReturnStatusMessage(user);
    }

    /**
     * Assign the role to the user, if there's no user in the database then the first user will be an 'Admin', any other user
     * registered after that is going to be assigned with a 'User' role
     *
     * @param user - the user to assign the role to
     */
    public void assignRoleToUser(User user) {
        if (getAllUsers().isEmpty()) {
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }
    }
}
