package com.dh.hospital.service;

import com.dh.hospital.dto.EspecialidadDto;
import com.dh.hospital.entity.Especialidad;

import java.util.Collection;

public interface EspecialidadService {

    public abstract Collection<Especialidad> findAll();

    public abstract EspecialidadDto save(EspecialidadDto especialidadDto);

    public abstract EspecialidadDto update(Long id, EspecialidadDto doctor);

    public void delete(Long id);

    public abstract EspecialidadDto findById(Long id);

}
