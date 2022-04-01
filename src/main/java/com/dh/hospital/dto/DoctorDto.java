package com.dh.hospital.dto;

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
    private HospitalDto hospital = new HospitalDto();
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


    @Override
    public String toString() {
        return "DoctorDto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", direccion='" + direccion + '\'' +
                ", hospitalId=" + hospital.getId() +
                '}';
    }
}
