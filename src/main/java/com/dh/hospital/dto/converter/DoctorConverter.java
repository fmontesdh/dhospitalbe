package com.dh.hospital.dto.converter;

import com.dh.hospital.dto.DoctorDto;
import com.dh.hospital.dto.EspecialidadDto;
import com.dh.hospital.entity.Doctor;
import com.dh.hospital.entity.Especialidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorConverter {

    @Autowired
    private HospitalConverter hospitalConverter;

    public DoctorDto entityToDto(Doctor doctor){
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(doctor.getId());
        doctorDto.setNombre(doctor.getNombre());
        doctorDto.setApellido(doctor.getApellido());
        doctorDto.setFechaNacimiento(doctor.getFechaNacimiento());
        doctorDto.setDireccion(doctor.getDireccion());
        doctorDto.setHospital(hospitalConverter.entityToDto(doctor.getHospital()));
        for(Especialidad esp : doctor.getEspecialidades()){
            doctorDto.getEspecialidades().add(new EspecialidadDto(esp.getId(), esp.getNombre(), esp.getDescripcion()));
        }
        return doctorDto;
    }

    public Doctor dtoToEntity(DoctorDto doctorDto){
        Doctor doctor = new Doctor();
        doctor.setId(doctorDto.getId());
        doctor.setNombre(doctorDto.getNombre());
        doctor.setApellido(doctorDto.getApellido());
        doctor.setFechaNacimiento(doctorDto.getFechaNacimiento());
        doctor.setDireccion(doctorDto.getDireccion());
        doctor.setHospital(hospitalConverter.dtoToEntity(doctorDto.getHospital()));
        for(EspecialidadDto esp : doctorDto.getEspecialidades()){
            doctor.getEspecialidades().add(new Especialidad(esp.getId(), esp.getNombre(), esp.getDescripcion()));
        }
        return doctor;
    }
}
