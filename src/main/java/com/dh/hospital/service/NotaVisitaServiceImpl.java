package com.dh.hospital.service;

import com.dh.hospital.entity.NotaVisita;
import com.dh.hospital.repository.NotaVisitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotaVisitaServiceImpl implements NotaVisitaService {

    @Autowired
    private NotaVisitaRepository notaVisitaRepository;

    @Override
    public Page<NotaVisita> findAll(Pageable pageable) {
        return notaVisitaRepository.findAll(pageable);
    }

    @Override
    public Optional<NotaVisita> findById(Long id) {
        return notaVisitaRepository.findById(id);
    }

    @Override
    public NotaVisita save(NotaVisita notaVisita) {
        return notaVisitaRepository.save(notaVisita);
    }

    @Override
    public Boolean update(Long id, NotaVisita notaVisita) {
        Optional<NotaVisita> notaOptional = notaVisitaRepository.findById(id);
        if (notaOptional.isPresent()) {
            NotaVisita notaEdit = notaOptional.get();
            notaEdit.setDescripcion(notaVisita.getDescripcion());
            notaEdit.setFechaNota(notaVisita.getFechaNota());
            notaEdit.setPaciente(notaVisita.getPaciente());
            notaEdit.setDoctor(notaVisita.getDoctor());
            notaEdit.setUpdatedBy(notaVisita.getUpdatedBy());
            notaVisitaRepository.save(notaEdit);
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<NotaVisita> notaOptional = notaVisitaRepository.findById(id);
        if (notaOptional.isPresent()) {
            notaVisitaRepository.delete(notaOptional.get());
            return true;
        }
        return false;
    }
}
