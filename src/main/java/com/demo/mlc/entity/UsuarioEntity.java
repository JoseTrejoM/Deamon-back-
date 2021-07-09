/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.mlc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 *
 * @author greser69
 */
@Data
@Entity
@Table(name = "TUSUARIOS")
public class UsuarioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USUARIOID")
    private Integer usuarioId;

    @Column(name="ROLID")
    private Integer rolId;

    @Column(name="TIPOUSUARIOID")
    private Integer tipoUsuarioId;

    @Column(name="ESTATUSUSUARIOID")
    private Integer estatusUsuarioId;

    @Column(name="PERSONAFISICAID")
    private Integer personaFisicaId;

    @Column(name="USUARIO")
    private String usuario;

    @Column(name="INICIALES")
    private String iniciales;
    
    @Column(name="CONTRASENA")
    private String contrasena;    
    
    @Column(name="PUESTO")
    private String puesto;

    @Column(name="AREA")
    private String area;

    @Column(name="INTENTOS")
    private Integer intentos;

    @Column(name="FECHAALTA")
    private Date fechaAlta;

    @Column(name="FECHAULTIMOACCESO")
    private Date fechaUltimoAcceso;

    @Column(name="FOTO")
    private String foto;

}
