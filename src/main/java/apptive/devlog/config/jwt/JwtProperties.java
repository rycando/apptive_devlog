package apptive.devlog.config.jwt;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;

@Setter
@Getter
@Validated
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    @NotNull
    private Duration expirationTime;
    @NotNull
    private String secret;
}
