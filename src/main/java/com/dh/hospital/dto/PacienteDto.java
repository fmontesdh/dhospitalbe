package com.dh.hospital.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PacienteDto {

    private Long id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String direccion;
    private HospitalDto hospital;
    private List<NotaVisitaDto> notasVisita = new ArrayList<>();

    public PacienteDto(){
    }

   public PacienteDto(Long id, String nombre, String apellido, Date fechaNacimiento, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
    }

    public PacienteDto(Long id, String nombre, String apellido, Date fechaNacimiento, String direccion, HospitalDto hospital) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.hospital = hospital;
    }
}
