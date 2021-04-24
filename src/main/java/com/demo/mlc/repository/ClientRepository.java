/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.mlc.repository;

import com.demo.mlc.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author greser69
 */
@Repository
public interface ClientRepository extends JpaRepository<ClienteEntity, Integer>{     
}
