package com.demo.mlc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.demo.mlc.dto.ErrorCodeDTO;
import com.demo.mlc.dto.LoginRequestDTO;
import com.demo.mlc.dto.LoginResponseDTO;
import com.demo.mlc.dto.ModuloDTO;
import com.demo.mlc.dto.PermisoDTO;
import com.demo.mlc.exception.ServiceException;
import com.demo.mlc.exception.utils.UtilsEx;
import com.demo.mlc.repository.ModuleRepository;
import com.demo.mlc.repository.PermissionRepository;
import com.demo.mlc.security.JwtService;
import com.demo.mlc.service.AuthService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public LoginResponseDTO validateUser(LoginRequestDTO userLogin) throws ServiceException {
        try {
            var auth = validateAuth(userLogin);
            String token = jwtService.createToken((LoginRequestDTO) auth.getPrincipal());

            var loginResponse = new LoginResponseDTO();
            loginResponse.setIdToken(token);
            return loginResponse;
        } catch (Exception e) {
            UtilsEx.showStackTraceError(e);
            throw UtilsEx.createServiceException(e);
        }
    }

    private Authentication validateAuth(LoginRequestDTO userLogin) throws ServiceException {
        try {
            userLogin.setPassword(userLogin.getUsername() + userLogin.getPassword());
            var userAuthToken = new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword());
            return authenticationManager.authenticate(userAuthToken);
        } catch (AuthenticationException e) {
            var errorCode = new ErrorCodeDTO();
            errorCode.setHttpStatus(HttpStatus.UNAUTHORIZED);
            errorCode.setMessage(errorCode.getHttpStatus().getReasonPhrase());
            throw new ServiceException(errorCode, errorCode.getMessage());
        }
    }

    @Override
    public List<ModuloDTO> modulesUser(Integer idSistemaPadre, Integer idUsuario) throws ServiceException {
        var listModules = moduleRepository.findBySisModPadreIdAndUsuarioId(idSistemaPadre, idUsuario);
        listModules.sort((a, b) -> Integer.compare(a.getOrder(), b.getOrder()));
        return listModules.stream().map(element -> modelMapper.map(element, ModuloDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PermisoDTO> permissionUser(Integer idSistema, Integer idUsuario)
            throws ServiceException {
        var listPermission = permissionRepository.findBySisModIdAndUsuarioId(idSistema,
                idUsuario);
        return listPermission.stream().map(element -> modelMapper.map(element, PermisoDTO.class))
                .collect(Collectors.toList());
    }

}
