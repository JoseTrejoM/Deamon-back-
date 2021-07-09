/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.mlc.service;

import java.util.List;

import com.demo.mlc.dto.UsuarioDTO;
import com.demo.mlc.exception.ServiceException;

/**
 *
 * @author greser69
 */

public interface UserService {
    
    UsuarioDTO createUser(UsuarioDTO user) throws ServiceException;         
    
    UsuarioDTO getUserById(Integer idUsuario) throws ServiceException;
    
    List<UsuarioDTO> getUserAll() throws ServiceException;
    
    UsuarioDTO updateUser(UsuarioDTO user) throws ServiceException;

    UsuarioDTO deleteUser(Integer idUsuario) throws ServiceException;
}
