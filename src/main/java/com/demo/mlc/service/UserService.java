/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.mlc.service;

import com.demo.mlc.dto.UserLoginResponse;
import com.demo.mlc.entity.UsuarioAccesoEntity;
import com.demo.mlc.exception.ServiceException;

/**
 *
 * @author greser69
 */

public interface UserService {
    
    UsuarioAccesoEntity createUser(UsuarioAccesoEntity user) throws ServiceException;
     
    UserLoginResponse validateUser(UsuarioAccesoEntity user) throws ServiceException;
}
