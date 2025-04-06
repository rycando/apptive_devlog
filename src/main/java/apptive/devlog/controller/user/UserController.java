package apptive.devlog.controller.user;

import apptive.devlog.controller.user.dto.CreateUserRequestDto;
import apptive.devlog.controller.user.dto.CreateUserResponseDto;
import apptive.devlog.service.user.UserService;
import apptive.devlog.service.user.dto.CreateUserDto;
import apptive.devlog.service.user.dto.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

//    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<CreateUserResponseDto> createUser(@Valid @RequestBody CreateUserRequestDto dto) {
//        CreateUserDto createUserDto = new CreateUserDto(dto.name, dto.nickname, dto.email, dto.gender, dto.birthday, dto.password);
//        UserDto userDto = this.userService.createUser(createUserDto);

//        return new ResponseEntity<CreateUserResponseDto>(CreateUserResponseDto.buildFromUserDto(userDto), HttpStatus.CREATED);
        return new ResponseEntity<CreateUserResponseDto>(new CreateUserResponseDto(), HttpStatus.CREATED);
    }
}
