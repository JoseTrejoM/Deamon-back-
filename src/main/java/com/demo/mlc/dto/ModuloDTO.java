package com.demo.mlc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModuloDTO {

    private Integer sisModId;    
    private String nombre;
    private String url;
    private String imagen;

}
