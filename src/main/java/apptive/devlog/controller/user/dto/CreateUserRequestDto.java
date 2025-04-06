package apptive.devlog.controller.user.dto;

import apptive.devlog.domain.user.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.Date;

@Getter
public class CreateUserRequestDto {
    @NotEmpty
    @Size(min = 2, max = 50)
    public String name;

    @NotEmpty
    @Email
    public String email;

    @NotEmpty
    public String password;

    @NotEmpty
    @Size(min = 2, max = 50)
    public String nickname;

    @NotNull
    public Date birthday;

    @NotNull
    public Gender gender;
}
