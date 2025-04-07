package apptive.devlog.controller.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class UserLoginRequestDto {
    @NotEmpty
    @Email
    String email;

    @NotEmpty
    String password;
}
