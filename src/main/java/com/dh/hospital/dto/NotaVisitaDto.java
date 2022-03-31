package com.dh.hospital.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NotaVisitaDto {

    private Long id;
    private String descripcion;
    private Date fechaNota;
    private PacienteDto pacienteDto;
    private DoctorDto doctorDto;

    public NotaVisitaDto(){
    }

    public NotaVisitaDto(Long id, String descripcion, Date fechaNota) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaNota = fechaNota;
    }

    public NotaVisitaDto(Long id, String descripcion, Date fechaNota, PacienteDto pacienteDto) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaNota = fechaNota;
        this.pacienteDto = pacienteDto;
    }

    public NotaVisitaDto(Long id, String descripcion, Date fechaNota, PacienteDto pacienteDto, DoctorDto doctorDto) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaNota = fechaNota;
        this.pacienteDto = pacienteDto;
        this.doctorDto = doctorDto;
    }
}
