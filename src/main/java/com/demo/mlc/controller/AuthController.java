package com.demo.mlc.controller;

import com.demo.mlc.dto.LoginRequestDTO;

import org.springframework.http.ResponseEntity;

public interface AuthController {
    ResponseEntity<Object> validateUser(LoginRequestDTO userLogin);
    
    ResponseEntity<Object> modulesUser(Integer idSistemaPadre, Integer idUsuario);

    ResponseEntity<Object> permissionUser(Integer idSistema, Integer idUsuario);
}
