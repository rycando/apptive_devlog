package apptive.devlog.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NICKNAME_ALREADY_USED(HttpStatus.UNPROCESSABLE_ENTITY, "Nickname is already used"),
    USER_ALREADY_EXIST(HttpStatus.UNPROCESSABLE_ENTITY, "User is already exist"),
    ;


    private final HttpStatus httpStatus;
    private final String message;
}
