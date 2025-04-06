package apptive.devlog.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HttpException extends RuntimeException {
    private final ErrorCode errorCode;
}