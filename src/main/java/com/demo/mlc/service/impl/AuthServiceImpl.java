package com.demo.mlc.service.impl;

import com.demo.mlc.dto.ErrorCodeDTO;
import com.demo.mlc.dto.LoginRequestDTO;
import com.demo.mlc.dto.LoginResponseDTO;
import com.demo.mlc.exception.ServiceException;
import com.demo.mlc.exception.utils.UtilsEx;
import com.demo.mlc.security.JwtService;
import com.demo.mlc.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Override
    public LoginResponseDTO validateUser(LoginRequestDTO userLogin) throws ServiceException {
        try {
            var auth = validateAuth(userLogin);
            String token = jwtService.createToken((LoginRequestDTO) auth.getPrincipal());

            var loginResponse = new LoginResponseDTO();
            loginResponse.setIdToken(token);
            return loginResponse;
        } catch (Exception e) {
            UtilsEx.showStackTraceError(e);
            throw UtilsEx.createServiceException(e);
        }
    }

    private Authentication validateAuth(LoginRequestDTO userLogin) throws ServiceException {
        try {
            var userAuthToken = new UsernamePasswordAuthenticationToken(userLogin.getUsername(),
                    userLogin.getPassword());
            return authenticationManager.authenticate(userAuthToken);
        } catch (AuthenticationException e) {
            var errorCode = new ErrorCodeDTO();
            errorCode.setHttpStatus(HttpStatus.UNAUTHORIZED);
            errorCode.setMessage(errorCode.getHttpStatus().getReasonPhrase());
            throw new ServiceException(errorCode, errorCode.getMessage());
        }
    }

}
