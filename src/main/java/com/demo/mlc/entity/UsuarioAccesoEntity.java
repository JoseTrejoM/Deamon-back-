/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.mlc.entity;

import java.io.Serializable;

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
@Table(name = "USUARIO_ACCESO")
public class UsuarioAccesoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IDUSUARIO")
    private Integer idUsuario;
    
    @Column(name="CORREO")
    private String correo;
    
    @Column(name="CONTRASENIA")
    private String contrasenia;
    
    @Column(name="TIPO")
    private String tipo;        

}
