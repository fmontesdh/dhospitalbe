package com.dh.hospital.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class HospitalDto {

    private Long id;
    private String nombre;
    private Date createAt;

    public HospitalDto(){
    }
}
