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
@Table(name = "CLIENTE")
public class ClienteEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IDCLIENTE")
    private Integer idCliente;
    
    @Column(name="NOMBRE")
    private String nombre;
    
    @Column(name="CURP")
    private String curp;
    
    @Column(name="FECHANAC")
    private Date fechaNac;
    
    @Column(name="SEXO")
    private String sexo;

}
