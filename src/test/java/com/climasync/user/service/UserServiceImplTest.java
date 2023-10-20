package com.climasync.user.service;

import com.climasync.ClimaSyncApplication;
import com.climasync.user.model.dto.RegisterRequest;
import com.climasync.user.model.entity.User;
import com.climasync.user.repository.UserRepository;
import com.climasync.weather.model.entity.CurrentWeather;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserServiceImplTest {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @BeforeEach
    void tearDown() {
        userService.deleteAll();
    }

    @AfterEach
    void deleteEverything() {
        userService.deleteAll();
    }

//    @Test
//    void testForDeployment() {
//        String test = "Test";
//        Assertions.assertThat(test).isEqualTo("Tast");
//    }

    @Test
    void firstUserConsistencyTest() {

        // Arrange
        RegisterRequest newUserData = RegisterRequest.builder()
                .firstName("Mike")
                .lastName("Tester")
                .email("newuser@test.com")
                .password("123456789")
                .build();

        // Act
        User newUser = modelMapper.map(newUserData, User.class);

        // Assertion
        Assertions.assertThat(newUser).isInstanceOf(User.class);

        Assertions.assertThat(newUser.getFirstName())
                .as("The user first name is not equal")
                .isEqualTo("Mike");

        Assertions.assertThat(newUser.getLastName())
                .as("The user last name is not equal")
                .isEqualTo("Tester");

        Assertions.assertThat(newUser.getEmail())
                .as("The user email is not equal")
                .isEqualTo("newuser@test.com");

        Assertions.assertThat(newUser.getPassword())
                .as("The user password is not equal")
                .isEqualTo("123456789");
    }

    @Test
    void createNewUserAndSaveToDatabase() {

        // Arrange
        RegisterRequest newUserData = RegisterRequest.builder()
                .firstName("Mike")
                .lastName("Tester")
                .email("newuser@test.com")
                .password("123456789")
                .build();

        User newUser = modelMapper.map(newUserData, User.class);

        // Act
        userService.assignRoleToUser(newUser);
        userService.saveUser(newUser);

        // Assert
        Assertions.assertThat(userService.getAllUsers())
                .as("The user wasn't persisted to the database")
                .isNotEmpty();
    }

}
