package com.demo.mlc.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.demo.mlc.entity.UsuarioEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)

// @AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginRequestDTO implements UserDetails {
    private UsuarioEntity usuarioAcceso;

    private String username;
    private String password;

    public LoginRequestDTO(UsuarioEntity usuarioAcceso) {
        this.usuarioAcceso = usuarioAcceso;
        this.username = usuarioAcceso.getUsuario();
        this.password = usuarioAcceso.getContrasena();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var listRols = new ArrayList<SimpleGrantedAuthority>();
        var listRolMock = Arrays.asList("API_USER", "API_CUSTOMER");
        listRolMock.forEach((String rol) -> 
            listRols.add(new SimpleGrantedAuthority(rol))
        );
        return listRols;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
