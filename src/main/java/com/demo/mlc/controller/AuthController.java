package com.demo.mlc.controller;

import com.demo.mlc.dto.LoginRequestDTO;

import org.springframework.http.ResponseEntity;

public interface AuthController {
    ResponseEntity<Object> validateUser(LoginRequestDTO userLogin);
}
