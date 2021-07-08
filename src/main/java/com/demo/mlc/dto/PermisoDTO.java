package com.demo.mlc.dto;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermisoDTO {

    private Integer permisoId;
    private String descripcionPermiso;
    
}
