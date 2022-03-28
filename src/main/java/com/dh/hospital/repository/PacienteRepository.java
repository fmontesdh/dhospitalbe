package com.dh.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dh.hospital.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
