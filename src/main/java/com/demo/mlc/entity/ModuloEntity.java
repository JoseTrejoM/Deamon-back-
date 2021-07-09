package com.demo.mlc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "VSISTEMAMODULO")
public class ModuloEntity implements Serializable{

    @Id
    @Column(name="SISMODID")
    private Integer sisModId;    

    @Column(name="SISMODPADREID")
    private Integer sisModPadreId;

    @Column(name="ORDEN")
    private Integer order;

    @Column(name="USUARIOID")
    private Integer usuarioId;

    @Column(name="NOMBRE")
    private String nombre;

    @Column(name="URL")
    private String url;

    @Column(name="IMAGEN")
    private String imagen;

}
