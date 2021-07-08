/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.mlc.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.demo.mlc.dto.CustomerDTO;
import com.demo.mlc.dto.ErrorCodeDTO;
import com.demo.mlc.entity.ClienteEntity;
import com.demo.mlc.exception.ServiceException;
import com.demo.mlc.exception.utils.UtilsEx;
import com.demo.mlc.repository.ClientRepository;
import com.demo.mlc.service.ClientService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author greser69
 */
@Service
public class ClientServiceImpl implements ClientService {
    private static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    ClientRepository clientRepository;

    @Override
    public CustomerDTO createClient(CustomerDTO customer) throws ServiceException {
        try {
            var customerEntity = modelMapper.map(customer, ClienteEntity.class);            
            return modelMapper.map(clientRepository.save(customerEntity), CustomerDTO.class);
        } catch (Exception e) {
            UtilsEx.showStackTraceError(e);
            throw UtilsEx.createServiceException(e);
        }
    }

    @Override
    public CustomerDTO getClientById(Integer idCliente) throws ServiceException {
        try {
            Optional<ClienteEntity> opClient = clientRepository.findById(idCliente);
            var errorCode = new ErrorCodeDTO();
            errorCode.setHttpStatus(HttpStatus.NOT_FOUND);
            errorCode.setMessage(errorCode.getHttpStatus().getReasonPhrase() + " with idCliente "  + idCliente);
            var customerEntity = opClient.orElseThrow(() -> new ServiceException(errorCode, errorCode.getMessage()));
            return modelMapper.map(customerEntity, CustomerDTO.class);
        } catch (Exception e) {
            UtilsEx.showStackTraceError(e);
            throw UtilsEx.createServiceException(e);
        }

    }

    @Override
    public List<CustomerDTO> getClientAll() throws ServiceException {
        try {
            var list = clientRepository.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
            return list.stream().map(element -> modelMapper.map(element, CustomerDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            UtilsEx.showStackTraceError(e);
            throw UtilsEx.createServiceException(e);
        }
    }

    @Override
    public CustomerDTO updateClient(CustomerDTO customer) throws ServiceException {

        getClientById(customer.getIdCliente());
        try {
            return createClient(customer);
        } catch (Exception e) {
            UtilsEx.showStackTraceError(e);
            throw UtilsEx.createServiceException(e);
        }
    }

    @Override
    public CustomerDTO deleteClient(Integer idCliente) throws ServiceException {

        var customerDTO = getClientById(idCliente);
        try {
            clientRepository.deleteById(idCliente);
            return customerDTO;
        } catch (Exception e) {
            UtilsEx.showStackTraceError(e);
            throw UtilsEx.createServiceException(e);
        }
    }

}
