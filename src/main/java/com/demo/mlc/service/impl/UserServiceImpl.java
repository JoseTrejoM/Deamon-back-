/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.mlc.service.impl;

import java.util.List;
import java.util.Optional;

import com.demo.mlc.dto.ErrorCode;
import com.demo.mlc.dto.UserLoginRequest;
import com.demo.mlc.dto.UserLoginResponse;
import com.demo.mlc.entity.UsuarioAccesoEntity;
import com.demo.mlc.exception.ServiceException;
import com.demo.mlc.exception.utils.UtilsException;
import com.demo.mlc.repository.UserRepository;
import com.demo.mlc.security.JwtService;
import com.demo.mlc.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 *
 * @author greser69
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Override
    public UsuarioAccesoEntity createUser(UsuarioAccesoEntity user) throws ServiceException {
        try {
            Optional<UsuarioAccesoEntity> opUsuario = userRepository.findByCorreo(user.getCorreo());

            if (opUsuario.isPresent()) {
                ErrorCode errorCode = new ErrorCode();
                errorCode.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
                errorCode.setMessage(errorCode.getHttpStatus().getReasonPhrase());
                throw new ServiceException(errorCode, errorCode.getMessage());
            }
            String passBCrypt = BCrypt.hashpw(user.getContrasenia(), BCrypt.gensalt());
            user.setContrasenia(passBCrypt);
            return userRepository.save(user);
        } catch (Exception e) {
            UtilsException.showStackTraceError(e);
            throw UtilsException.createServiceException(e);
        }
    }

    @Override
    public UserLoginResponse validateUser(UserLoginRequest userLogin) throws ServiceException {
        try {
            Optional<UsuarioAccesoEntity> opUsuario = userRepository.findByCorreo(userLogin.getUsername());
            ErrorCode errorCode = new ErrorCode();
            errorCode.setHttpStatus(HttpStatus.UNAUTHORIZED);
            errorCode.setMessage(errorCode.getHttpStatus().getReasonPhrase());
            var opUser = opUsuario.orElseThrow(() -> new ServiceException(errorCode, errorCode.getMessage()));            
            
            final UserLoginResponse loginResponse = new UserLoginResponse();
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword());
                try {
                    authenticationManager.authenticate(authentication);
                    String token = jwtService.createToken(new UserLoginRequest(opUser));
                    loginResponse.setIdToken(token);
                } catch (AuthenticationException e) {
                    throw new ServiceException(errorCode, errorCode.getMessage());
                }

            return loginResponse;
        } catch (Exception e) {
            UtilsException.showStackTraceError(e);
            throw UtilsException.createServiceException(e);
        }
    }
    
    @Override
	public UsuarioAccesoEntity getUserById(Integer idUsuario) throws ServiceException {
    	try {
            Optional<UsuarioAccesoEntity> opUser = userRepository.findById(idUsuario);
            ErrorCode errorCode = new ErrorCode();
            errorCode.setHttpStatus(HttpStatus.NOT_FOUND);
            errorCode.setMessage(errorCode.getHttpStatus().getReasonPhrase());
            return opUser.orElseThrow(() -> new ServiceException(errorCode, errorCode.getMessage()));
        } catch (Exception e) {
            UtilsException.showStackTraceError(e);
            throw UtilsException.createServiceException(e);
        }
	}
    
    @Override
    public List<UsuarioAccesoEntity> getUserAll() throws ServiceException {
        try {
            return userRepository.findAll(Sort.by(Sort.Direction.ASC, "correo"));
        } catch (Exception e) {
            UtilsException.showStackTraceError(e);
            throw UtilsException.createServiceException(e);
        }
    }	

	@Override
	public UsuarioAccesoEntity updateUser(UsuarioAccesoEntity user) throws ServiceException {
		UsuarioAccesoEntity userEntity = getUserById(user.getIdUsuario());
        try {
            if(!userEntity.getContrasenia().equals(user.getContrasenia())){
                String passBCrypt = BCrypt.hashpw(user.getContrasenia(), BCrypt.gensalt());
                user.setContrasenia(passBCrypt);
            }
            userEntity = userRepository.save(user);
            return userEntity;
        } catch (Exception e) {
            UtilsException.showStackTraceError(e);
            throw UtilsException.createServiceException(e);
        }
	}

	@Override
	public UsuarioAccesoEntity deleteUser(Integer idUsuario) throws ServiceException {
		UsuarioAccesoEntity userEntity = getUserById(idUsuario);
        try {
            userRepository.deleteById(idUsuario);
            return userEntity;
        } catch (Exception e) {
            UtilsException.showStackTraceError(e);
            throw UtilsException.createServiceException(e);
        }
	}
}
