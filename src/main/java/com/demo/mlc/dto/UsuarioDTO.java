package com.demo.mlc.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO {
    private Integer usuarioId;
    private Integer rolId;
    private Integer tipoUsuarioId;
    private Integer estatusUsuarioId;
    private Integer personaFisicaId;
    private String usuario;
    private String iniciales;
    private String contrasena;
    private String puesto;
    private String area;
    private Integer intentos;
    private Date fechaAlta;
    private Date fechaUltimoAcceso;
    private String foto;
}
