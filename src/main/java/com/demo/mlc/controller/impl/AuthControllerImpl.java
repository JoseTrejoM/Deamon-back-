package com.demo.mlc.controller.impl;

import com.demo.mlc.controller.AuthController;
import com.demo.mlc.dto.LoginRequestDTO;
import com.demo.mlc.exception.ServiceException;
import com.demo.mlc.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // esta sera la raiz de la url, es decir http://127.0.0.1:8080/api/
@CrossOrigin
public class AuthControllerImpl implements AuthController {

    @Autowired
    AuthService authService;

    @Override
    @PostMapping("/auth/login")
    public ResponseEntity<Object> validateUser(@RequestBody LoginRequestDTO userLogin) {
        try {
            var userLoginResponse = authService.validateUser(userLogin);
            return ResponseEntity.status(HttpStatus.OK).body(userLoginResponse);
        } catch (ServiceException e) {
            return new ResponseEntity<>(e.getCode(), e.getCode().getHttpStatus());
        }
    }

}
