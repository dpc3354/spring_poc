package com.employees.spring_boot_poc.config;

import com.employees.spring_boot_poc.exception.PocAPIException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.security.Key;


@Component
public class JwtTokenUtil {

    @Value("${jwt-secret}")
    private String jwtSecret;

    @Value("${jwt-expiration}")
    private long jwtExpirationDate;

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();

        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException exception) {
            throw new PocAPIException(HttpStatus.BAD_REQUEST, "INVALID JWT TOKEN");
        } catch (ExpiredJwtException exception) {
            throw new PocAPIException(HttpStatus.BAD_REQUEST, "EXPIRED JWT TOKEN");
        } catch (UnsupportedJwtException exception) {
            throw new PocAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT TOKEN");
        } catch (IllegalArgumentException exception) {
            throw new PocAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty");
        }
    }
}
