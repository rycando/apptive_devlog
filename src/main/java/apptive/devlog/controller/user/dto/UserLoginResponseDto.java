package apptive.devlog.controller.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginResponseDto {
    private String jwtToken;
}
