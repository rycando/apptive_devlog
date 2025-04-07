package apptive.devlog.controller.user;

import apptive.devlog.controller.user.dto.CreateUserRequestDto;
import apptive.devlog.controller.user.dto.CreateUserResponseDto;
import apptive.devlog.controller.user.dto.LeaveUserResponseDto;
import apptive.devlog.domain.user.User;
import apptive.devlog.service.user.UserService;
import apptive.devlog.service.user.dto.CreateUserDto;
import apptive.devlog.service.user.dto.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<CreateUserResponseDto> createUser(@Valid @RequestBody CreateUserRequestDto dto) {
        CreateUserDto createUserDto = new CreateUserDto(dto.name, dto.nickname, dto.email, dto.gender, dto.birthday, dto.password);
        UserDto userDto = this.userService.createUser(createUserDto);

        return new ResponseEntity<>(CreateUserResponseDto.buildFromUserDto(userDto), HttpStatus.CREATED);
    }

    @PostMapping("/leave")
    @PreAuthorize("isAuthenticated() and hasRole('USER')")
    public ResponseEntity<LeaveUserResponseDto> deleteUser(@AuthenticationPrincipal User user) {
//        LeaveUserDto leaveUserDto = new LeaveUserDto(user.getId());
//        LeaveUserResultDto leaveUserResultDto = this.userService.leaveUser(leaveUserDto);

//        return new ResponseEntity<>(LeaveUserResponseDto.buildFromLeaveUserResultDto(leaveUserResultDto), HttpStatus.OK);
        return new ResponseEntity<>(new LeaveUserResponseDto(), HttpStatus.OK);
    }
}
