/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.mlc.service.impl;

import com.demo.mlc.dto.ErrorCode;
import com.demo.mlc.dto.UserLoginResponse;
import com.demo.mlc.entity.UsuarioAccesoEntity;
import com.demo.mlc.exception.ServiceException;
import com.demo.mlc.exception.utils.UtilsException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.mlc.repository.UserRepository;
import com.demo.mlc.service.UserService;
import java.util.Base64;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author greser69
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UsuarioAccesoEntity createUser(UsuarioAccesoEntity user) throws ServiceException {
        try {
            Optional<UsuarioAccesoEntity> opUsuario = userRepository.findByCorreo(user.getCorreo());

            if (opUsuario.isPresent()) {
                ErrorCode errorCode = new ErrorCode();
                errorCode.setMessage("User exists");
                throw new ServiceException(errorCode);
            }
            return userRepository.save(user);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw UtilsException.createServiceException(e);
        }
    }

    @Override
    public UserLoginResponse validateUser(UsuarioAccesoEntity user) throws ServiceException {
        try {
            Optional<UsuarioAccesoEntity> opUsuario = userRepository.findByCorreo(user.getCorreo());
            final UserLoginResponse loginResponse = new UserLoginResponse();

            opUsuario.ifPresent((opUser) -> {
                if (opUser != null && opUser.getCorreo().equalsIgnoreCase(user.getCorreo()) && opUser.getContrasenia().equals(user.getContrasenia())) {
                    loginResponse.setUser(opUser.getCorreo());

                    //30minutos = 1_800_000 milisegundos
                    long timeSesion = 1_800_000l;
                    long timeInMillis = System.currentTimeMillis() + timeSesion;
                    loginResponse.setExpiresIn(timeInMillis);

                    String src = opUser.getCorreo() + timeInMillis;
                    String token = Base64.getEncoder().encodeToString(src.getBytes());
                    loginResponse.setIdToken(token);
                }
            });

            if (loginResponse.getIdToken() == null) {
                ErrorCode errorCode = new ErrorCode();
                errorCode.setMessage("Access denied");
                throw new ServiceException(errorCode, errorCode.getMessage());
            }
            return loginResponse;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw UtilsException.createServiceException(e);
        }
    }
    
    @Override
	public UsuarioAccesoEntity getUserById(Integer idUsuario) throws ServiceException {
    	try {
            Optional<UsuarioAccesoEntity> opUser = userRepository.findById(idUsuario);
            ErrorCode errorCode = new ErrorCode();
            errorCode.setMessage("Not exists " + idUsuario);
            return opUser.orElseThrow(() -> new ServiceException(errorCode));           
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw UtilsException.createServiceException(e);
        }
	}
    
    @Override
    public List<UsuarioAccesoEntity> getUserAll() throws ServiceException {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw UtilsException.createServiceException(e);
        }
    }	

	@Override
	public UsuarioAccesoEntity updateUser(UsuarioAccesoEntity user) throws ServiceException {
		UsuarioAccesoEntity userEntity = getUserById(user.getIdUsuario());
        try {
            userEntity = userRepository.save(user);
            return userEntity;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
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
            throw UtilsException.createServiceException(e);
        }
	}
}
