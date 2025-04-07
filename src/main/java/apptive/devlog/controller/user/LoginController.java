package apptive.devlog.controller.user;

import apptive.devlog.filter.JwtAuthenticationFilter;
import apptive.devlog.controller.user.dto.UserLoginRequestDto;
import apptive.devlog.controller.user.dto.UserLoginResponseDto;
import apptive.devlog.service.auth.TokenService;
import apptive.devlog.service.auth.dto.UserLoginDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/auth")
public class LoginController {
    private final TokenService tokenService;

    @PostMapping("/token")
    public ResponseEntity<UserLoginResponseDto> userLogin(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto) {
        UserLoginDto userLoginDto = new UserLoginDto(userLoginRequestDto.getEmail(), userLoginRequestDto.getPassword());
        String jwtToken = this.tokenService.login(userLoginDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtAuthenticationFilter.HEADER_AUTHORIZATION, JwtAuthenticationFilter.TOKEN_PREFIX + jwtToken);

        return new ResponseEntity<>(new UserLoginResponseDto(jwtToken), httpHeaders, HttpStatus.OK);
    }
}
