package apptive.devlog.utils;

import apptive.devlog.config.jwt.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final JwtProperties jwtProperties;
    private SecretKey secretKey;
    private static final String AUTHORITIES_KEY = "auth";

    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtProperties.getSecret())
        );
    }

    public String generateToken(Authentication authentication) {
        Date now = new Date();
        Date expireAt = new Date(now.getTime() + jwtProperties.getExpirationTime().toMillis());

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuedAt(now)
                .setExpiration(expireAt)
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString()
                .split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public String getUserEmail(String token) {
        Claims claims = getClaims(token);

        return claims.getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
