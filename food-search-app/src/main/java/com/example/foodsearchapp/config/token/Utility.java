package com.example.foodsearchapp.config.token;

import com.example.foodsearchapp.service.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class Utility {

    @Value("${app.jsonWebTokenSecret}")
    private String jsonWebTokenSecret;

    @Value("${app.jsonWebTokenExpirationMillisecond}")
    private int jsonWebTokenExpirationMillisecond;

    public String generateJsonWebToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jsonWebTokenExpirationMillisecond))
                .signWith(SignatureAlgorithm.HS512, jsonWebTokenSecret)
                .compact();
    }

    public boolean validateJsonWebToken(String jwt) {
        try {
            Jwts.parser().setSigningKey(jsonWebTokenSecret).parseClaimsJws(jwt);
            return true;
        } catch (MalformedJwtException | IllegalArgumentException e) {
            log.error(e.getMessage());
        }
        return false;
    }

    public String getUserNameFromJsonWebToken(String jwt) {
        return Jwts.parser().setSigningKey(jsonWebTokenSecret).parseClaimsJws(jwt).getBody().getSubject();
    }
}
