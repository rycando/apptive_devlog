package apptive.devlog.service.user.dto;

import apptive.devlog.domain.user.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class UserDto {
    private String id;
    private String name;
    private String nickname;
    private String email;
    private Gender gender;
    private Date birthday;
}
