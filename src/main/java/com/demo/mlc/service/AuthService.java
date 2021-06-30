package com.demo.mlc.service;

import com.demo.mlc.dto.LoginRequestDTO;
import com.demo.mlc.dto.LoginResponseDTO;
import com.demo.mlc.exception.ServiceException;

public interface AuthService {
    LoginResponseDTO validateUser(LoginRequestDTO userLogin) throws ServiceException;
}
