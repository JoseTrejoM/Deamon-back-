/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.mlc.controller;

import com.demo.mlc.dto.CustomerDTO;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author greser69
 */
public interface ClientController {
    
    ResponseEntity<Object> createClient(CustomerDTO customer);
    
    ResponseEntity<Object> getClientById (Integer idCliente);
    
    ResponseEntity<Object> getClientAll();       
    
    ResponseEntity<Object> updateClient(CustomerDTO customer);
    
    ResponseEntity<Object> deleteClient(Integer idCliente);
}
