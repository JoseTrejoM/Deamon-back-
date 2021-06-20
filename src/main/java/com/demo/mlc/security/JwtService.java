package com.demo.mlc.security;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    @Value("${secret_jwt_seed}")
    private String SECRET_KEY;

    @Value("${session_timeout}")
    private int EXPIRATION_TIME;

    public String createToken(UserDetails userDetails) {
        String username = userDetails.getUsername();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        var listAuthorities = new ArrayList<String>();
        authorities.forEach((data) -> {
            listAuthorities.add(data.getAuthority());
        });

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MILLISECOND, EXPIRATION_TIME);

        return JWT.create().withSubject(username).withClaim(JwtConstants.AUTHORITIES, listAuthorities)
                .withIssuer(username).withExpiresAt(cal.getTime()).sign(getAlgorithm());
    }

    public Boolean hasTokenExpired(String token) {
        JWT.require(getAlgorithm()).build().verify(token).getExpiresAt().before(new Date());

        return JWT.decode(token).getExpiresAt().before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (userDetails.getUsername().equals(username) && !hasTokenExpired(token));

    }

    public String extractUsername(String token) {
        return JWT.decode(token).getSubject();
    }

    public Collection<? extends GrantedAuthority> getAuthorities(String token) {
        return JWT.decode(token).getClaim(JwtConstants.AUTHORITIES).asList(GrantedAuthority.class);
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(SECRET_KEY);
    }
}
