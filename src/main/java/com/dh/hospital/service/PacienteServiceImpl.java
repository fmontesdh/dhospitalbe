package com.dh.hospital.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dh.hospital.dto.DoctorDto;
import com.dh.hospital.dto.NotaVisitaDto;
import com.dh.hospital.dto.PacienteDto;
import com.dh.hospital.dto.converter.PacienteConverter;
import com.dh.hospital.entity.Hospital;
import com.dh.hospital.entity.NotaVisita;
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

    @Autowired
    private PacienteConverter pacienteConverter;

    @Override
    public Page<Paciente> findAll(Pageable pageable) {
        return pacienteRepository.findAll(pageable);
    }

    @Override
    public Optional<PacienteDto> findById(Long id) {
        PacienteDto pacienteDto = pacienteConverter.entityToDto(pacienteRepository.findById(id).get());
        Optional<PacienteDto> optDto = Optional.of(pacienteDto);
        return optDto;
    }

    @Override
    public PacienteDto save(PacienteDto pacienteDto) {
        Paciente paciente = pacienteConverter.dtoToEntity(pacienteDto);
        paciente.setCreatedBy(1);
        return pacienteConverter.entityToDto(pacienteRepository.save(paciente));
    }

    @Override
    public PacienteDto update(Long id, PacienteDto pacienteDto) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if (pacienteOptional.isPresent()) {
            Paciente pacienteEdit = pacienteOptional.get();
            pacienteEdit.setNombre(pacienteDto.getNombre());
            pacienteEdit.setApellido(pacienteDto.getApellido());
            pacienteEdit.setFechaNacimiento(pacienteDto.getFechaNacimiento());
            pacienteEdit.setDireccion(pacienteDto.getDireccion());
            pacienteEdit.setHospital(new Hospital(pacienteDto.getHospital().getId()));
            pacienteEdit.setUpdatedBy(1);
            return pacienteConverter.entityToDto(pacienteRepository.save(pacienteEdit));
        }
        return null;
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

    @Override
    public PacienteDto findDoctorNotasByIdPaciente(Long id) {
        Paciente paciente = pacienteRepository.findDoctorNotasByIdPaciente(id);
        PacienteDto pacienteDto = new PacienteDto(paciente.getId(), paciente.getNombre(), paciente.getApellido(),paciente.getFechaNacimiento(),paciente.getDireccion());
        for (NotaVisita nv : paciente.getNotasVisita()){
            pacienteDto.getNotasVisita().add(new NotaVisitaDto(nv.getId(), nv.getDescripcion(), nv.getFechaNota(), new DoctorDto(nv.getDoctor().getId(), nv.getDoctor().getNombre(), nv.getDoctor().getApellido())));
        }
        return pacienteDto;
    }

}
