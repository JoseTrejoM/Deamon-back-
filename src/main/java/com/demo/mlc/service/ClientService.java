/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.mlc.service;

import com.demo.mlc.dto.CustomerDTO;
import com.demo.mlc.exception.ServiceException;
import java.util.List;

/**
 *
 * @author greser69
 */
public interface ClientService {

    CustomerDTO createClient(CustomerDTO customer) throws ServiceException;

    CustomerDTO getClientById(Integer idCliente) throws ServiceException;

    List<CustomerDTO> getClientAll() throws ServiceException;

    CustomerDTO updateClient(CustomerDTO customer) throws ServiceException;

    CustomerDTO deleteClient(Integer idCliente) throws ServiceException;
}
