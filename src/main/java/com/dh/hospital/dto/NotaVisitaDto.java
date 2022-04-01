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
    private PacienteDto paciente;
    private DoctorDto doctor;

    public NotaVisitaDto(){
    }

    public NotaVisitaDto(Long id, String descripcion, Date fechaNota) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaNota = fechaNota;
    }

    public NotaVisitaDto(Long id, String descripcion, Date fechaNota, DoctorDto doctor) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaNota = fechaNota;
        this.doctor = doctor;
    }

    public NotaVisitaDto(Long id, String descripcion, Date fechaNota, PacienteDto paciente) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaNota = fechaNota;
        this.paciente = paciente;
    }

    public NotaVisitaDto(Long id, String descripcion, Date fechaNota, PacienteDto paciente, DoctorDto doctor) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaNota = fechaNota;
        this.paciente = paciente;
        this.doctor = doctor;
    }
}
