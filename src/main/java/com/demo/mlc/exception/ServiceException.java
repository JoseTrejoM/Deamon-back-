/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.mlc.exception;

import com.demo.mlc.dto.ErrorCodeDTO;

/**
 *
 * @author greser69
 */
public class ServiceException extends Exception {

    private final ErrorCodeDTO code;

    public ServiceException(ErrorCodeDTO code) {
        super();
        this.code = code;
    }

    public ServiceException(ErrorCodeDTO code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(ErrorCodeDTO code, Throwable cause) {
        super(cause);
        this.code = code;
    }
    
    public ServiceException(ErrorCodeDTO code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ErrorCodeDTO getCode() {
        return this.code;
    }
}
