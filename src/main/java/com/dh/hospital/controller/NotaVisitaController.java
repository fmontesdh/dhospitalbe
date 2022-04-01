package com.dh.hospital.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import com.dh.hospital.dto.DoctorDto;
import com.dh.hospital.dto.NotaVisitaDto;
import com.dh.hospital.dto.PacienteDto;
import com.dh.hospital.entity.NotaVisita;
import com.dh.hospital.service.DoctorService;
import com.dh.hospital.service.NotaVisitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.data.domain.Pageable;

import com.dh.hospital.service.PacienteService;

@RestController
@RequestMapping("/api/nota")
public class NotaVisitaController {

    @Autowired
    private NotaVisitaService notaVisitaService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public ResponseEntity<Page<NotaVisita>> index(Pageable pageable) {
        return ResponseEntity.ok(notaVisitaService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaVisitaDto> show(@PathVariable Long id) {
        Optional<NotaVisitaDto> notaOptional = notaVisitaService.findById(id);
        if (!notaOptional.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(notaOptional.get());
    }

    @PostMapping
    public ResponseEntity<NotaVisitaDto> store(@Valid @RequestBody NotaVisitaDto notaVisitaDto) {
        Optional<PacienteDto> pacienteOptional = pacienteService.findById(notaVisitaDto.getPaciente().getId());
        if (!pacienteOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        notaVisitaDto.setPaciente(pacienteOptional.get());

        DoctorDto doctor = doctorService.findById(notaVisitaDto.getDoctor().getId());
        if (doctor == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        notaVisitaDto.setDoctor(doctor);
        notaVisitaDto = notaVisitaService.save(notaVisitaDto);

        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(notaVisitaDto.getId()).toUri();

        return ResponseEntity.created(ubicacion).body(notaVisitaDto);
    }
}
