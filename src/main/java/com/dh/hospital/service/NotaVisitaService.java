package com.dh.hospital.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dh.hospital.entity.NotaVisita;

public interface NotaVisitaService {

    public abstract Page<NotaVisita> findAll(Pageable pageable);

    public abstract Optional<NotaVisita> findById(Long id);

    public abstract NotaVisita save(NotaVisita notaVisita);

    public abstract Boolean update(Long id, NotaVisita notaVisita);

    public abstract Boolean delete(Long id);
}
