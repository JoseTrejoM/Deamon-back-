package com.demo.mlc.security;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.demo.mlc.dto.LoginRequestDTO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    @Value("${secret_jwt_seed}")
    private String secretKey;

    @Value("${session_timeout}")
    private int expirationTime;

    public String createToken(UserDetails userDetails) {
        var loginRequestDTO = (LoginRequestDTO) userDetails;
        String username = userDetails.getUsername();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        List<String> listAuthorities = authorities.stream().map(GrantedAuthority :: getAuthority).collect(Collectors.toList());

        var cal = Calendar.getInstance();
        cal.add(Calendar.MILLISECOND, expirationTime);

        return JWT.create().withSubject(username).withClaim(JwtConstants.AUTHORITIES, listAuthorities)
                .withIssuer(loginRequestDTO.getUsuarioAcceso().getUsuarioId().toString()).withExpiresAt(cal.getTime()).sign(getAlgorithm());
    }    

    public boolean validateToken(String token, UserDetails userDetails) {
        return isUserTokenValid(token, userDetails) && !hasTokenExpired(token);
    }

    public String extractUsername(String token) {
        return JWT.decode(token).getSubject();
    }

    public Date extractExpiresAt(String token) {
        return JWT.decode(token).getExpiresAt();
    }

    public Collection<GrantedAuthority> getAuthorities(String token) {
        return JWT.decode(token).getClaim(JwtConstants.AUTHORITIES).asList(GrantedAuthority.class);
    }

    private boolean hasTokenExpired(String token) {
        return JWT.require(getAlgorithm()).build().verify(token).getExpiresAt().before(new Date());
    }

    private boolean isUserTokenValid(String token, UserDetails userDetails) {
        return JWT.require(getAlgorithm()).build().verify(token).getSubject().equals(userDetails.getUsername());
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secretKey);
    }
}
