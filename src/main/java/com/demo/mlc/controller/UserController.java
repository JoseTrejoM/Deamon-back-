/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.mlc.controller;

import com.demo.mlc.dto.UsuarioDTO;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author greser69
 */
public interface UserController {

	ResponseEntity<Object> createUser(UsuarioDTO user);
	
	ResponseEntity<Object> getUserById (Integer idUsuario);

	ResponseEntity<Object> getUserAll();

	ResponseEntity<Object> updateUser(UsuarioDTO user);

	ResponseEntity<Object> deleteUser(Integer idUsuario);
}
