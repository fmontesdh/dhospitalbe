package com.dh.hospital.service;

import java.util.List;
import java.util.Optional;

import com.dh.hospital.dto.PacienteDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dh.hospital.entity.Paciente;

public interface PacienteService {

    Page<Paciente> findAll(Pageable pageable);

    Optional<PacienteDto> findById(Long id);

    PacienteDto save(PacienteDto pacienteDto);

    PacienteDto update(Long id, PacienteDto pacienteDto);

    Boolean delete(Long id);

    PacienteDto findDoctorNotasByIdPaciente(Long id);

}
