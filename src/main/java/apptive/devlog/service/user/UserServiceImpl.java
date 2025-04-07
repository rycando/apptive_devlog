package apptive.devlog.service.user;

import apptive.devlog.domain.user.User;
import apptive.devlog.domain.user.role.Role;
import apptive.devlog.domain.user.role.RoleName;
import apptive.devlog.exception.ErrorCode;
import apptive.devlog.exception.HttpException;
import apptive.devlog.repository.user.RoleRepository;
import apptive.devlog.repository.user.UserRepository;
import apptive.devlog.service.user.dto.CreateUserDto;
import apptive.devlog.service.user.dto.UserDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDto createUser(CreateUserDto dto) throws HttpException {
        this.userRepository.findByEmail(dto.email)
                .ifPresent(user -> {
                    throw new HttpException(ErrorCode.USER_ALREADY_EXIST);
                });
        this.userRepository.findByNickname(dto.nickname)
                .ifPresent(user -> {
                    throw new HttpException(ErrorCode.NICKNAME_ALREADY_USED);
                });
        Role userRole = this.roleRepository.findByName(RoleName.USER)
                .orElseThrow(() -> new HttpException(ErrorCode.ROLE_NOT_FOUND));


        User user = User.builder()
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .gender(dto.getGender())
                .birthday(dto.getBirthday())
                .roles(Collections.singletonList(userRole))
                .build();

        user = this.userRepository.save(user);

        return new UserDto(user.getId(), user.getName(), user.getNickname(), user.getEmail(), user.getGender(), user.getBirthday());
    }
}
