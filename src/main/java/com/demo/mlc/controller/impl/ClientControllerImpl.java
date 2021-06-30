/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.mlc.controller.impl;

import com.demo.mlc.controller.ClientController;
import com.demo.mlc.dto.CustomerDTO;
import com.demo.mlc.exception.ServiceException;
import com.demo.mlc.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author greser69
 */
@RestController
@CrossOrigin
@RequestMapping("/api") //esta sera la raiz de la url, es decir http://127.0.0.1:8080/api/
public class ClientControllerImpl implements ClientController {

    @Autowired
    ClientService clientService;

    @Override
    @PostMapping("/client/create")
    public ResponseEntity<Object> createClient(@RequestBody CustomerDTO customer) {
        try {
            var clientNew = clientService.createClient(customer);
            return ResponseEntity.status(HttpStatus.OK).body(clientNew);
        } catch (ServiceException e) {
            return new ResponseEntity<>(e.getCode(), e.getCode().getHttpStatus());
        }
    }

    @Override
    @GetMapping("/client/byid/{idCliente}")
    public ResponseEntity<Object> getClientById(@PathVariable Integer idCliente) {
        try {
            var clientGet = clientService.getClientById(idCliente);
            return ResponseEntity.status(HttpStatus.OK).body(clientGet);

        } catch (ServiceException e) {
            return new ResponseEntity<>(e.getCode(), e.getCode().getHttpStatus());
        }
    }

    @Override
    @GetMapping("/client/all")
    public ResponseEntity<Object> getClientAll() {
        try {
            var listClient = clientService.getClientAll();
            return ResponseEntity.status(HttpStatus.OK).body(listClient);

        } catch (ServiceException e) {
            return new ResponseEntity<>(e.getCode(), e.getCode().getHttpStatus());
        }
    }

    @Override
    @PutMapping("/client/update")
    public ResponseEntity<Object> updateClient(@RequestBody CustomerDTO customer) {
        try {
            var clientUpdate = clientService.updateClient(customer);
            return ResponseEntity.status(HttpStatus.OK).body(clientUpdate);

        } catch (ServiceException e) {
            return new ResponseEntity<>(e.getCode(), e.getCode().getHttpStatus());
        }
    }

    @Override
    @DeleteMapping("/client/delete/{idCliente}")
    public ResponseEntity<Object> deleteClient(@PathVariable Integer idCliente) {
        try {
            var clientDelete =  clientService.deleteClient(idCliente);
            return ResponseEntity.status(HttpStatus.OK).body(clientDelete);

        } catch (ServiceException e) {
            return new ResponseEntity<>(e.getCode(), e.getCode().getHttpStatus());
        }
    }

}
