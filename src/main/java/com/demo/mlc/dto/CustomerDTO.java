package com.demo.mlc.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {
    private Integer idCliente;
    private String nombre;
    private String curp;
    private Date fechaNac;
    private String sexo;
}
