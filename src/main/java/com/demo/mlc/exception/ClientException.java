/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.mlc.exception;

/**
 *
 * @author greser69
 */
public class ClientException extends Exception {

    private final String code;

    public ClientException(String code) {
        super();
        this.code = code;
    }

    public ClientException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ClientException(String code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public ClientException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
