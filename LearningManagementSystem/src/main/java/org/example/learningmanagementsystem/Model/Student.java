package org.example.learningmanagementsystem.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    @NotEmpty(message = "ID can't be empty")
    private String id;
    @NotEmpty(message = "Name can't be empty")
    private String name;
    @NotEmpty(message = "Email can't be empty")
    @Email
    private String email;
    @NotEmpty(message = "password can't be empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$", message = "Password must be at least 8 characters and contain uppercase and lowercase letters, number and special character")
    private String password;
    @NotEmpty(message = "Status can't be empty")
    @Pattern(regexp = "(?i)^(Active|Inactive|Suspended)",message = "Status should be one of these Active, Inactive or Suspended")
    private String status;
}
