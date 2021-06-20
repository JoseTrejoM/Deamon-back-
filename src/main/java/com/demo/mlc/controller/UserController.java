/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.mlc.controller;

import com.demo.mlc.dto.UserLoginRequest;
import com.demo.mlc.entity.UsuarioAccesoEntity;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author greser69
 */
public interface UserController {

	ResponseEntity<Object> createUser(UsuarioAccesoEntity user);

	ResponseEntity<Object> validateUser(UserLoginRequest userLogin);
	
	ResponseEntity<Object> getUserById (Integer idUsuario);

	ResponseEntity<Object> getUserAll();

	ResponseEntity<Object> updateUser(UsuarioAccesoEntity user);

	ResponseEntity<Object> deleteUser(Integer idUsuario);
}
