package com.dh.hospital.service;

import com.dh.hospital.dto.EspecialidadDto;
import com.dh.hospital.dto.converter.EspecialidadConverter;
import com.dh.hospital.entity.Especialidad;
import com.dh.hospital.exception.ResourceNotFoundException;
import com.dh.hospital.repository.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class EspecialidadServiceImpl implements EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private EspecialidadConverter especialidadConverter;

    @Override
    public Collection<Especialidad> findAll() {
        return especialidadRepository.findAll();
    }

    @Override
    public EspecialidadDto save(EspecialidadDto especialidadDto) {
        Especialidad especialidad = especialidadConverter.dtoToEntity(especialidadDto);
        especialidad.setCreatedBy(1);
        return especialidadConverter.entityToDto(especialidadRepository.save(especialidad));
    }

    @Override
    public EspecialidadDto update(Long id, EspecialidadDto especialidadDto) {
        Optional<Especialidad> doctorOptional = especialidadRepository.findById(id);
        if (doctorOptional.isPresent()) {
            Especialidad especialidadEdit = doctorOptional.get();
            especialidadEdit.setNombre(especialidadDto.getNombre());
            especialidadEdit.setDescripcion(especialidadDto.getDescripcion());
            especialidadEdit.setUpdatedBy(1);
            return especialidadConverter.entityToDto(especialidadRepository.save(especialidadEdit));
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        especialidadRepository.deleteById(id);
    }

    @Override
    public EspecialidadDto findById(Long id) {
        Optional<Especialidad> doctorOptional = especialidadRepository.findById(id);
        Optional<EspecialidadDto> doctorDtoOptional = Optional.of(especialidadConverter.entityToDto(doctorOptional.get()));
        return doctorDtoOptional.orElseThrow(()->new ResourceNotFoundException("Not found id " + id));
    }

}
