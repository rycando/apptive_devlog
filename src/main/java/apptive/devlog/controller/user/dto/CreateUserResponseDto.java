package apptive.devlog.controller.user.dto;

//import apptive.devlog.domain.user.Gender;
//import apptive.devlog.service.user.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class CreateUserResponseDto {
    private String id;
    private String name;
    private String nickname;
    private String email;
//    private Gender gender;
    private Date birthday;

//    public static CreateUserResponseDto buildFromUserDto(UserDto userDto) {
//        CreateUserResponseDto createUserResponseDto = new CreateUserResponseDto();
//
//        createUserResponseDto.id = userDto.getId();
//        createUserResponseDto.name = userDto.getName();
//        createUserResponseDto.nickname = userDto.getNickname();
//        createUserResponseDto.email = userDto.getEmail();
//        createUserResponseDto.gender = userDto.getGender();
//        createUserResponseDto.birthday = userDto.getBirthday();
//
//        return createUserResponseDto;
//    }
}
