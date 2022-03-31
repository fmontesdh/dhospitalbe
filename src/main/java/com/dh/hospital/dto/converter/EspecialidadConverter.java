package com.dh.hospital.dto.converter;

import com.dh.hospital.dto.EspecialidadDto;
import com.dh.hospital.entity.Especialidad;
import org.springframework.stereotype.Component;

@Component
public class EspecialidadConverter {

    public EspecialidadDto entityToDto(Especialidad especialidad){
        EspecialidadDto especialidadDto = new EspecialidadDto();
        especialidadDto.setId(especialidad.getId());
        especialidadDto.setNombre(especialidad.getNombre());
        especialidadDto.setDescripcion(especialidad.getDescripcion());
        especialidadDto.setAvatarPath(especialidad.getAvatarPath());
        return especialidadDto;
    }

    public Especialidad dtoToEntity(EspecialidadDto especialidadDto){
        Especialidad especialidad = new Especialidad();
        especialidad.setId(especialidadDto.getId());
        especialidad.setNombre(especialidadDto.getNombre());
        especialidad.setDescripcion(especialidadDto.getDescripcion());
        especialidad.setAvatarPath(especialidadDto.getAvatarPath());
        return especialidad;
    }
}
