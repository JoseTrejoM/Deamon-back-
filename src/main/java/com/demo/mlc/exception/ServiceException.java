/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.mlc.exception;

import com.demo.mlc.dto.ErrorCode;

/**
 *
 * @author greser69
 */
public class ServiceException extends Exception {

    private final ErrorCode code;

    public ServiceException(ErrorCode code) {
        super();
        this.code = code;
    }

    public ServiceException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(ErrorCode code, Throwable cause) {
        super(cause);
        this.code = code;
    }
    
    public ServiceException(ErrorCode code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ErrorCode getCode() {
        return this.code;
    }
}
