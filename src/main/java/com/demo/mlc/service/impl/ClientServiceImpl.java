/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.mlc.service.impl;

import com.demo.mlc.dto.ErrorCode;
import com.demo.mlc.entity.ClienteEntity;
import com.demo.mlc.exception.ServiceException;
import com.demo.mlc.exception.utils.UtilsException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.mlc.repository.ClientRepository;
import com.demo.mlc.service.ClientService;

/**
 *
 * @author greser69
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public ClienteEntity createClient(ClienteEntity client) throws ServiceException {
        try {
            return clientRepository.save(client);
        } catch (Exception e) {
            UtilsException.showStackTraceError(e);
            throw UtilsException.createServiceException(e);
        }
    }

    @Override
    public ClienteEntity getClientById(Integer idCliente) throws ServiceException {
        try {
            Optional<ClienteEntity> opClient = clientRepository.findById(idCliente);
            ErrorCode errorCode = new ErrorCode();
            errorCode.setMessage("Not exists customer with idCliente " + idCliente);
            return opClient.orElseThrow(() -> new ServiceException(errorCode, errorCode.getMessage()));
        } catch (Exception e) {
            UtilsException.showStackTraceError(e);
            throw UtilsException.createServiceException(e);
        }

    }

    @Override
    public List<ClienteEntity> getClientAll() throws ServiceException {
        try {
            return clientRepository.findAll();
        } catch (Exception e) {
            UtilsException.showStackTraceError(e);
            throw UtilsException.createServiceException(e);
        }
    }

    @Override
    public ClienteEntity updateClient(ClienteEntity client) throws ServiceException {

        ClienteEntity clienteEntity = getClientById(client.getIdCliente());
        try {
            clienteEntity = clientRepository.save(client);
            return clienteEntity;
        } catch (Exception e) {
            UtilsException.showStackTraceError(e);
            throw UtilsException.createServiceException(e);
        }
    }

    @Override
    public ClienteEntity deleteClient(Integer idCliente) throws ServiceException {

        ClienteEntity clienteEntity = getClientById(idCliente);
        try {
            clientRepository.deleteById(idCliente);
            return clienteEntity;
        } catch (Exception e) {
            UtilsException.showStackTraceError(e);
            throw UtilsException.createServiceException(e);
        }
    }
}
