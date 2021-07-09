/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.mlc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 *
 * @author greser69
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorCodeDTO implements Serializable {

    @JsonIgnore
    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    private String codeError;
    private String message;
}
