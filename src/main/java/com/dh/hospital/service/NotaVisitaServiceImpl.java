package com.dh.hospital.service;

import com.dh.hospital.dto.NotaVisitaDto;
import com.dh.hospital.dto.converter.NotaVisitaConverter;
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

    @Autowired
    private NotaVisitaConverter notaVisitaConverter;

    @Override
    public Page<NotaVisita> findAll(Pageable pageable) {
        return notaVisitaRepository.findAll(pageable);
    }

    @Override
    public Optional<NotaVisitaDto> findById(Long id) {
        Optional<NotaVisita> notaOptional= notaVisitaRepository.findById(id);
        Optional<NotaVisitaDto> notaDtoOptional = Optional.of(notaVisitaConverter.entityToDto(notaOptional.get()));
        return notaDtoOptional;
    }

    @Override
    public NotaVisitaDto save(NotaVisitaDto notaVisitaDto) {
        NotaVisita notaVisita = notaVisitaConverter.dtoToEntity(notaVisitaDto);
        notaVisita.setCreatedBy(1);
        return notaVisitaConverter.entityToDto(notaVisitaRepository.save(notaVisita));
    }

    @Override
    public Boolean update(Long id, NotaVisitaDto notaVisitaDto) {
        Optional<NotaVisita> notaOptional = notaVisitaRepository.findById(id);
        if (notaOptional.isPresent()) {
            NotaVisita notaEdit = notaOptional.get();
            notaEdit.setDescripcion(notaVisitaDto.getDescripcion());
            notaEdit.setFechaNota(notaVisitaDto.getFechaNota());
            notaEdit.getPaciente().setId(notaVisitaDto.getPaciente().getId());
            notaEdit.getDoctor().setId(notaVisitaDto.getDoctor().getId());
            notaEdit.setUpdatedBy(1);
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
