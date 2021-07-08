/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.mlc.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.demo.mlc.dto.ErrorCodeDTO;
import com.demo.mlc.dto.UsuarioDTO;
import com.demo.mlc.entity.UsuarioEntity;
import com.demo.mlc.exception.ServiceException;
import com.demo.mlc.exception.utils.UtilsEx;
import com.demo.mlc.repository.UserRepository;
import com.demo.mlc.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 *
 * @author greser69
 */
@Service
public class UserServiceImpl implements UserService {
    private static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private UserRepository userRepository;

    @Override
    public UsuarioDTO createUser(UsuarioDTO user) throws ServiceException {
        try {            
            Optional<UsuarioEntity> opUsuario = userRepository.findByUsuario(user.getUsuario());

            if (opUsuario.isPresent()) {
                var errorCode = new ErrorCodeDTO();
                errorCode.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
                errorCode.setMessage(errorCode.getHttpStatus().getReasonPhrase());
                throw new ServiceException(errorCode, errorCode.getMessage());
            }
            user.setContrasena(user.getUsuario() + user.getContrasena());
            String passBCrypt = BCrypt.hashpw(user.getContrasena(), BCrypt.gensalt());
            var userEntity = modelMapper.map(user, UsuarioEntity.class);
            userEntity.setContrasena(passBCrypt);
            return modelMapper.map(userRepository.save(userEntity), UsuarioDTO.class);
        } catch (Exception e) {
            UtilsEx.showStackTraceError(e);
            throw UtilsEx.createServiceException(e);
        }
    }    
    
    @Override
	public UsuarioDTO getUserById(Integer idUsuario) throws ServiceException {
    	try {
            Optional<UsuarioEntity> opUser = userRepository.findById(idUsuario);
            var errorCode = new ErrorCodeDTO();
            errorCode.setHttpStatus(HttpStatus.NOT_FOUND);
            errorCode.setMessage(errorCode.getHttpStatus().getReasonPhrase() + " with idUsuario "  + idUsuario);
            var userEntity = opUser.orElseThrow(() -> new ServiceException(errorCode, errorCode.getMessage()));

            return modelMapper.map(userEntity, UsuarioDTO.class);
        } catch (Exception e) {
            UtilsEx.showStackTraceError(e);
            throw UtilsEx.createServiceException(e);
        }
	}
    
    @Override
    public List<UsuarioDTO> getUserAll() throws ServiceException {
        try {
            var list = userRepository.findAll(Sort.by(Sort.Direction.ASC, "usuario"));
            return list.stream().map(element -> modelMapper.map(element, UsuarioDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            UtilsEx.showStackTraceError(e);
            throw UtilsEx.createServiceException(e);
        }
    }	

	@Override
	public UsuarioDTO updateUser(UsuarioDTO user) throws ServiceException {
		var userDTO = getUserById(user.getUsuarioId());
        try {
            if(!userDTO.getContrasena().equals(user.getContrasena())){
                user.setContrasena(user.getUsuario() + user.getContrasena());
                String passBCrypt = BCrypt.hashpw(user.getContrasena(), BCrypt.gensalt());
                user.setContrasena(passBCrypt);
            }
            var userEntity = modelMapper.map(user, UsuarioEntity.class);
            return modelMapper.map(userRepository.save(userEntity), UsuarioDTO.class);
        } catch (Exception e) {
            UtilsEx.showStackTraceError(e);
            throw UtilsEx.createServiceException(e);
        }
	}

	@Override
	public UsuarioDTO deleteUser(Integer idUsuario) throws ServiceException {
		var userDTO = getUserById(idUsuario);
        try {
            userRepository.deleteById(idUsuario);
            return userDTO;
        } catch (Exception e) {
            UtilsEx.showStackTraceError(e);
            throw UtilsEx.createServiceException(e);
        }
	}

}
