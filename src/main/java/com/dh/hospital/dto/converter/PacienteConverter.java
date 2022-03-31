package com.dh.hospital.dto.converter;

import com.dh.hospital.dto.NotaVisitaDto;
import com.dh.hospital.dto.PacienteDto;
import com.dh.hospital.entity.NotaVisita;
import com.dh.hospital.entity.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteConverter {

    @Autowired
    private HospitalConverter hospitalConverter;

    public PacienteDto entityToDto(Paciente paciente){
        PacienteDto pacienteDto = new PacienteDto();
        pacienteDto.setId(paciente.getId());
        pacienteDto.setNombre(paciente.getNombre());
        pacienteDto.setApellido(paciente.getApellido());
        pacienteDto.setFechaNacimiento(paciente.getFechaNacimiento());
        pacienteDto.setDireccion(paciente.getDireccion());
        pacienteDto.setHospital(hospitalConverter.entityToDto(paciente.getHospital()));
        for(NotaVisita nota : paciente.getNotasVisita()){
            pacienteDto.getNotasVisita().add(new NotaVisitaDto(nota.getId(), nota.getDescripcion(), nota.getFechaNota()));
        }
        return pacienteDto;
    }

    public Paciente dtoToEntity(PacienteDto pacienteDto){
        Paciente paciente = new Paciente();
        paciente.setId(pacienteDto.getId());
        paciente.setNombre(pacienteDto.getNombre());
        paciente.setApellido(pacienteDto.getApellido());
        paciente.setFechaNacimiento(pacienteDto.getFechaNacimiento());
        paciente.setDireccion(pacienteDto.getDireccion());
        paciente.setHospital(hospitalConverter.dtoToEntity(pacienteDto.getHospital()));
        return paciente;
    }
}
