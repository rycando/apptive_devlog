package apptive.devlog.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String message;
    private final String path;

    public static ResponseEntity<ErrorResponse> toResponseEntity(HttpServletRequest request, ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getHttpStatus().value())
                        .error(errorCode.getHttpStatus().name())
                        .message(errorCode.getMessage())
                        .path(request.getRequestURI())
                        .build()
                );
    }
}
