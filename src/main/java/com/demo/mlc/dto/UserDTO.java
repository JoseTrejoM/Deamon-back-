package com.demo.mlc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private Integer idUsuario;
    private String correo;
    private String contrasenia;
    private String tipo;
}
