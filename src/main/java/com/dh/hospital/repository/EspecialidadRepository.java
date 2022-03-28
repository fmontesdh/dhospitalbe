package com.dh.hospital.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dh.hospital.entity.Especialidad;

@Repository
public interface EspecialidadRepository extends CrudRepository<Especialidad, Long> {
    Collection<Especialidad> findAll();
}
