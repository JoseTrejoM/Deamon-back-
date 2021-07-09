package com.demo.mlc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "VMODULOPERMISO")
public class PermisoEntity implements Serializable{

    @Id
    @Column(name="PERMISOID")
    private Integer permisoId;

    @Column(name="DESCRIPCIONPERMISO")
    private String descripcionPermiso;

    @Column(name="SISMODID")
    private Integer sisModId;

    @Column(name="SISMODPADREID")
    private Integer sisModPadreId;

    @Column(name="USUARIOID")
    private Integer usuarioId;    

}
