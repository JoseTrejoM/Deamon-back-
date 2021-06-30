package com.demo.mlc.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private ApiUserDetailService apiUserDetailService;

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader(JwtConstants.HEADER_AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith(JwtConstants.HEADER_TOKEN_PREFIX)) {
            String token = authorizationHeader.replace(JwtConstants.HEADER_TOKEN_PREFIX, "");
            String username = jwtService.extractUsername(token);

            var userDetails = apiUserDetailService.loadUserByUsername(username);
            if (jwtService.validateToken(token, userDetails)) {
                var authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);

    }

}
