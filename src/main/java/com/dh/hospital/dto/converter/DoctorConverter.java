package com.dh.hospital.dto.converter;

import com.dh.hospital.dto.DoctorDto;
import com.dh.hospital.dto.EspecialidadDto;
import com.dh.hospital.entity.Doctor;
import com.dh.hospital.entity.Especialidad;
import org.springframework.stereotype.Component;

@Component
public class DoctorConverter {

    public DoctorDto entityToDto(Doctor doctor){
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(doctor.getId());
        doctorDto.setNombre(doctor.getNombre());
        doctorDto.setApellido(doctor.getApellido());
        doctorDto.setFechaNacimiento(doctor.getFechaNacimiento());
        doctorDto.setDireccion(doctor.getDireccion());
        doctorDto.setHospitalId(doctor.getHospital().getId());
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
        doctor.getHospital().setId(doctorDto.getHospitalId());
        return doctor;
    }
}
