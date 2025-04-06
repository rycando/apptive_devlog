package apptive.devlog.service.user;

import apptive.devlog.service.user.dto.CreateUserDto;
import apptive.devlog.service.user.dto.UserDto;

public interface UserService {
    public UserDto createUser(CreateUserDto createUserDto);
}
