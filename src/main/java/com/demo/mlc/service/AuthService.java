package com.demo.mlc.service;

import java.util.List;

import com.demo.mlc.dto.LoginRequestDTO;
import com.demo.mlc.dto.LoginResponseDTO;
import com.demo.mlc.dto.ModuloDTO;
import com.demo.mlc.dto.PermisoDTO;
import com.demo.mlc.exception.ServiceException;

public interface AuthService {
    LoginResponseDTO validateUser(LoginRequestDTO userLogin) throws ServiceException;

    List<ModuloDTO> modulesUser(Integer idSistemaPadre, Integer idUsuario) throws ServiceException;

    List<PermisoDTO> permissionUser(Integer idSistema, Integer idUsuario) throws ServiceException;
}
