package com.dh.hospital.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dh.hospital.entity.Paciente;

public interface PacienteService {

    public abstract Page<Paciente> findAll(Pageable pageable);

    public abstract Optional<Paciente> findById(Long id);

    public abstract Paciente save(Paciente Paciente);

    public abstract Boolean update(Long id, Paciente Paciente);

    public abstract Boolean delete(Long id);

}
