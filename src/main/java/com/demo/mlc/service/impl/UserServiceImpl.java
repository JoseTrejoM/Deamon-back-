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
import com.demo.mlc.dto.UserDTO;
import com.demo.mlc.entity.UsuarioAccesoEntity;
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
    public UserDTO createUser(UserDTO user) throws ServiceException {
        try {            
            Optional<UsuarioAccesoEntity> opUsuario = userRepository.findByCorreo(user.getCorreo());

            if (opUsuario.isPresent()) {
                var errorCode = new ErrorCodeDTO();
                errorCode.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
                errorCode.setMessage(errorCode.getHttpStatus().getReasonPhrase());
                throw new ServiceException(errorCode, errorCode.getMessage());
            }
            String passBCrypt = BCrypt.hashpw(user.getContrasenia(), BCrypt.gensalt());
            var userEntity = convertToEntity(user);
            userEntity.setContrasenia(passBCrypt);
            return convertToDTO(userRepository.save(userEntity));
        } catch (Exception e) {
            UtilsEx.showStackTraceError(e);
            throw UtilsEx.createServiceException(e);
        }
    }    
    
    @Override
	public UserDTO getUserById(Integer idUsuario) throws ServiceException {
    	try {
            Optional<UsuarioAccesoEntity> opUser = userRepository.findById(idUsuario);
            var errorCode = new ErrorCodeDTO();
            errorCode.setHttpStatus(HttpStatus.NOT_FOUND);
            errorCode.setMessage(errorCode.getHttpStatus().getReasonPhrase() + " with idUsuario "  + idUsuario);
            var userEntity = opUser.orElseThrow(() -> new ServiceException(errorCode, errorCode.getMessage()));
            return convertToDTO(userEntity);
        } catch (Exception e) {
            UtilsEx.showStackTraceError(e);
            throw UtilsEx.createServiceException(e);
        }
	}
    
    @Override
    public List<UserDTO> getUserAll() throws ServiceException {
        try {
            var list = userRepository.findAll(Sort.by(Sort.Direction.ASC, "correo"));            
            return list.stream().map(this :: convertToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            UtilsEx.showStackTraceError(e);
            throw UtilsEx.createServiceException(e);
        }
    }	

	@Override
	public UserDTO updateUser(UserDTO user) throws ServiceException {
		var userDTO = getUserById(user.getIdUsuario());
        try {
            if(!userDTO.getContrasenia().equals(user.getContrasenia())){
                String passBCrypt = BCrypt.hashpw(user.getContrasenia(), BCrypt.gensalt());
                user.setContrasenia(passBCrypt);
            }
            var userEntity = convertToEntity(user);
            return convertToDTO(userRepository.save(userEntity));
        } catch (Exception e) {
            UtilsEx.showStackTraceError(e);
            throw UtilsEx.createServiceException(e);
        }
	}

	@Override
	public UserDTO deleteUser(Integer idUsuario) throws ServiceException {
		var userDTO = getUserById(idUsuario);
        try {
            userRepository.deleteById(idUsuario);
            return userDTO;
        } catch (Exception e) {
            UtilsEx.showStackTraceError(e);
            throw UtilsEx.createServiceException(e);
        }
	}

    private UsuarioAccesoEntity convertToEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, UsuarioAccesoEntity.class);
    }

    private UserDTO convertToDTO(UsuarioAccesoEntity userEntity){
        return modelMapper.map(userEntity, UserDTO.class);
    }
}
