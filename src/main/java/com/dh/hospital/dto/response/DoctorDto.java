package com.dh.hospital.dto.response;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class DoctorDto {

    private Long id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String direccion;
    private Long hospitalId;
    private List<EspecialidadDto> especialidades = new ArrayList<>();

    public DoctorDto(){
    }

   public DoctorDto(Long id, String nombre, String apellido, Date fechaNacimiento, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
    }

    public DoctorDto(Long id, String nombre, String apellido, Date fechaNacimiento, String direccion, Long hospitalId) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.hospitalId = hospitalId;
    }
}
