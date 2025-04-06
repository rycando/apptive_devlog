package apptive.devlog.service.user.dto;

import apptive.devlog.domain.user.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class CreateUserDto {
    public String name;
    public String nickname;
    public String email;
    public Gender gender;
    public Date birthday;
    public String password;
}
