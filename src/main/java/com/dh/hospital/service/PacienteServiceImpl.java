package com.dh.hospital.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dh.hospital.entity.Paciente;
import com.dh.hospital.repository.PacienteRepository;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public Page<Paciente> findAll(Pageable pageable) {
        return pacienteRepository.findAll(pageable);
    }

    @Override
    public Optional<Paciente> findById(Long id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public Paciente save(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public Boolean update(Long id, Paciente paciente) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if (pacienteOptional.isPresent()) {
            Paciente pacienteEdit = pacienteOptional.get();
            pacienteEdit.setNombre(paciente.getNombre());
            pacienteEdit.setApellido(paciente.getApellido());
            pacienteEdit.setFechaNacimiento(paciente.getFechaNacimiento());
            pacienteEdit.setDireccion(paciente.getDireccion());
            pacienteEdit.setHospital(paciente.getHospital());
            pacienteEdit.setUpdatedBy(paciente.getUpdatedBy());
            pacienteRepository.save(pacienteEdit);
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if (pacienteOptional.isPresent()) {
            pacienteRepository.delete(pacienteOptional.get());
            return true;
        }
        return false;
    }
}
