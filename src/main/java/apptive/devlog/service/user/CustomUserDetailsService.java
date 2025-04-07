package apptive.devlog.service.user;

import apptive.devlog.domain.user.role.Role;
import apptive.devlog.domain.user.User;
import apptive.devlog.exception.ErrorCode;
import apptive.devlog.exception.HttpException;
import apptive.devlog.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws HttpException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new HttpException(ErrorCode.USER_NOT_FOUND));

        user.setAuthorities(getRoles(user.getRoles()));

        return user;
    }

    private List<SimpleGrantedAuthority> getRoles(List<Role> roles) {
        return roles.stream()
                .map(role -> role.getName().getName())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
