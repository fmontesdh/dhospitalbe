package com.dh.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dh.hospital.entity.Paciente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query("select p " +
           "from Paciente p " +
           "inner join p.notasVisita nv " +
           "inner join nv.doctor " +
           "where p.id = :id")
    Paciente findDoctorNotasByIdPaciente(@Param("id") Long id);
}
