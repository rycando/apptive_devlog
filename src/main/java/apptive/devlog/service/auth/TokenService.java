package apptive.devlog.service.auth;

import apptive.devlog.utils.JwtUtil;
import apptive.devlog.service.auth.dto.UserLoginDto;
import apptive.devlog.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final JwtUtil jwtUtil;
//    private final RefreshTokenService refreshTokenService;
    private final UserService userService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public String login(UserLoginDto userLoginDto) {
        return createToken(userLoginDto.getEmail(), userLoginDto.getPassword());
    }

//    public String createNewAccessToken(String refreshToken) {
//        if (!jwtUtil.validateToken(refreshToken)) {
//            throw new IllegalArgumentException("Invalid refresh token");
//        }

//        String userEmail = refreshTokenService.findByRefreshToken(refreshToken).getUserEmail();
//        User user = userService.findByEmail(userEmail);

//        return createToken(user.getEmail(), user.getPassword());
//        return "";
//    }

    private String createToken(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtUtil.generateToken(authentication);
    }
}
