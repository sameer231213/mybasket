package com.mybasket.app.dto;


import com.mybasket.app.validations.ValidImageUrl;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Integer userId;
    @NotBlank
    private String name;

    @Email(message = "Invalid email formate")
    @NotBlank(message = "user email is required")
    private String email;
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{5,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit and be at least 5 characters long"
    )
    private String password;
    @ValidImageUrl
    private String userImageUrl;


}
