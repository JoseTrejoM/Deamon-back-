/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.mlc.controller.impl;

import com.demo.mlc.dto.UserLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.mlc.controller.UserController;
import com.demo.mlc.entity.UsuarioAccesoEntity;
import com.demo.mlc.exception.ServiceException;
import com.demo.mlc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author greser69
 */
@Slf4j
@RestController
@RequestMapping("/api") //esta sera la raiz de la url, es decir http://127.0.0.1:8080/api/
public class UserControllerImpl implements UserController{    
    
    @Autowired
    UserService userService;
    
    @Override
    @PostMapping("/user/create")
    public ResponseEntity<Object> createUser(@RequestBody UsuarioAccesoEntity user) {
        try {
            UsuarioAccesoEntity userNew = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(userNew);
        } catch (ServiceException e) {
            return new ResponseEntity<>(e.getCode(), e.getCode().getHttpStatus());
        }
    }

    @Override    
    @PostMapping("/user/login")
    public ResponseEntity<Object> validateUser(@RequestBody UsuarioAccesoEntity user) {
        try{
            log.info("Entro a validar usuario" + user);
         UserLoginResponse userLoginResponse = userService.validateUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(userLoginResponse);
        }catch(ServiceException e){
            return new ResponseEntity<>(e.getCode(), e.getCode().getHttpStatus());
        }
    }    
}
