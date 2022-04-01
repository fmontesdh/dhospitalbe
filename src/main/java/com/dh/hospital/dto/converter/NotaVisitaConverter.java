package com.dh.hospital.dto.converter;

import com.dh.hospital.dto.NotaVisitaDto;
import com.dh.hospital.entity.NotaVisita;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotaVisitaConverter {

    @Autowired
    private PacienteConverter pacienteConverter;

    @Autowired
    private DoctorConverter doctorConverter;

    public NotaVisitaDto entityToDto(NotaVisita notaVisita){
        NotaVisitaDto notaVisitaDto = new NotaVisitaDto();
        notaVisitaDto.setId(notaVisita.getId());
        notaVisitaDto.setDescripcion(notaVisita.getDescripcion());
        notaVisitaDto.setFechaNota(notaVisita.getFechaNota());
        notaVisitaDto.setPaciente(pacienteConverter.entityToDto(notaVisita.getPaciente()));
        notaVisitaDto.setDoctor(doctorConverter.entityToDto(notaVisita.getDoctor()));
        return notaVisitaDto;
    }

    public NotaVisita dtoToEntity(NotaVisitaDto notaVisitaDto){
        NotaVisita notaVisita = new NotaVisita();
        notaVisita.setId(notaVisitaDto.getId());
        notaVisita.setDescripcion(notaVisitaDto.getDescripcion());
        notaVisita.setFechaNota(notaVisitaDto.getFechaNota());
        notaVisita.setPaciente(pacienteConverter.dtoToEntity(notaVisitaDto.getPaciente()));
        notaVisita.setDoctor(doctorConverter.dtoToEntity(notaVisitaDto.getDoctor()));
        return notaVisita;
    }
}
