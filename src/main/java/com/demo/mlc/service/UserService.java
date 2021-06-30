/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.mlc.service;

import java.util.List;

import com.demo.mlc.dto.UserDTO;
import com.demo.mlc.exception.ServiceException;

/**
 *
 * @author greser69
 */

public interface UserService {
    
    UserDTO createUser(UserDTO user) throws ServiceException;         
    
    UserDTO getUserById(Integer idUsuario) throws ServiceException;
    
    List<UserDTO> getUserAll() throws ServiceException;
    
    UserDTO updateUser(UserDTO user) throws ServiceException;

    UserDTO deleteUser(Integer idUsuario) throws ServiceException;
}
