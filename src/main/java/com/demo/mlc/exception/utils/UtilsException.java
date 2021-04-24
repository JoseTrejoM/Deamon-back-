/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.mlc.exception.utils;

import com.demo.mlc.dto.ErrorCode;
import com.demo.mlc.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author greser69
 */
@Slf4j
public class UtilsException {

    public static ServiceException createServiceException(Exception e) {
        if (e instanceof ServiceException) {
            return (ServiceException) e;
        } else {
            ErrorCode errorCode = new ErrorCode();
            errorCode.setMessage(e.getMessage());
            return new ServiceException(errorCode);
        }
    }
}
