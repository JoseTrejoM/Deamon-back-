package com.demo.mlc.repository;

import java.util.List;

import com.demo.mlc.entity.ModuloEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<ModuloEntity, Integer>{
    List<ModuloEntity> findBySisModPadreIdAndUsuarioId(Integer sisModPadreId, Integer usuarioId);
}
