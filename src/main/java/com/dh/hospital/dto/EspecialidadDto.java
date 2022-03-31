package com.dh.hospital.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EspecialidadDto {

    private Long id;
    private String nombre;
    private String descripcion;

    public EspecialidadDto(){
    }

    public EspecialidadDto(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
