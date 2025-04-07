package apptive.devlog.service.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginDto {
    private String email;
    private String password;
}
