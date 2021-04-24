/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.mlc.service;

import com.demo.mlc.entity.ClienteEntity;
import com.demo.mlc.exception.ServiceException;
import java.util.List;

/**
 *
 * @author greser69
 */
public interface ClientService {

    ClienteEntity createClient(ClienteEntity client) throws ServiceException;

    ClienteEntity getClientById(Integer idCliente) throws ServiceException;

    List<ClienteEntity> getClientAll() throws ServiceException;

    ClienteEntity updateClient(ClienteEntity client) throws ServiceException;

    ClienteEntity deleteClient(Integer idCliente) throws ServiceException;
}
