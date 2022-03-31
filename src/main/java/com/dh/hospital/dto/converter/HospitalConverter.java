package com.dh.hospital.dto.converter;

import com.dh.hospital.dto.HospitalDto;
import com.dh.hospital.entity.Hospital;
import org.springframework.stereotype.Component;

@Component
public class HospitalConverter {

    public HospitalDto entityToDto(Hospital hospital){
        HospitalDto hospitalDto = new HospitalDto();
        hospitalDto.setId(hospital.getId());
        hospitalDto.setNombre(hospital.getNombre());
        return hospitalDto;
    }

    public Hospital dtoToEntity(HospitalDto hospitalDto){
        Hospital hospital = new Hospital();
        hospital.setId(hospitalDto.getId());
        hospital.setNombre(hospitalDto.getNombre());
        return hospital;
    }
}
