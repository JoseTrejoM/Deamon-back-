/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.mlc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author greser69
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponseDTO implements Serializable {
        
    private String user;
    private String idlocal;
    private String refreshToken;    
    private Long expiresIn;
    private String idToken;
}
