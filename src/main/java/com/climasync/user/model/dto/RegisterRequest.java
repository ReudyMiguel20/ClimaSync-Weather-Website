package com.climasync.user.model.dto;

import com.climasync.user.model.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    @NotEmpty(message = "First Name cannot be empty")
    @NotBlank(message = "First Name cannot be blank")
    @JsonProperty(value = "first_name")
    private String firstName;

    @NotEmpty(message = "Last Name cannot be empty")
    @NotBlank(message = "Last Name cannot be blank")
    @JsonProperty(value = "last_name")
    private String lastName;

    @NotEmpty(message = "Email cannot be empty")
    @NotBlank(message = "Email cannot be blank")
    @Email(regexp = "^(?i)[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 7, message = "Password must be at least 7 character long")
    private String password;

    @JsonIgnore
    private Role role;

}
