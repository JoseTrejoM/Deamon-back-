package com.demo.mlc.security;

import java.util.Optional;

import com.demo.mlc.dto.UserLoginRequest;
import com.demo.mlc.entity.UsuarioAccesoEntity;
import com.demo.mlc.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApiUserDetailService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioAccesoEntity> opUsuario = userRepository.findByCorreo(username);        
        var opUser = opUsuario.orElseThrow(() -> new UsernameNotFoundException("Username does not exist"));
        return new UserLoginRequest(opUser);
    }
    
}
