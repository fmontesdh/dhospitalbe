package com.dh.hospital.service;

import java.util.Optional;

import com.dh.hospital.dto.NotaVisitaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dh.hospital.entity.NotaVisita;

public interface NotaVisitaService {

    public abstract Page<NotaVisita> findAll(Pageable pageable);

    public abstract Optional<NotaVisitaDto> findById(Long id);

    public abstract NotaVisitaDto save(NotaVisitaDto notaVisitaDto);

    public abstract Boolean update(Long id, NotaVisitaDto notaVisitaDto);

    public abstract Boolean delete(Long id);
}
