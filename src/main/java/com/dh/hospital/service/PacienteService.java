package com.dh.hospital.service;

import java.util.Optional;

import com.dh.hospital.dto.PacienteDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dh.hospital.entity.Paciente;

public interface PacienteService {

    public abstract Page<Paciente> findAll(Pageable pageable);

    public abstract Optional<PacienteDto> findById(Long id);

    public abstract PacienteDto save(PacienteDto Paciente);

    public abstract PacienteDto update(Long id, PacienteDto Paciente);

    public abstract Boolean delete(Long id);

}
