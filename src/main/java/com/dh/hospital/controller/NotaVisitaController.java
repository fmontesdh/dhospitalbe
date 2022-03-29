package com.dh.hospital.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import com.dh.hospital.entity.Doctor;
import com.dh.hospital.entity.NotaVisita;
import com.dh.hospital.service.DoctorService;
import com.dh.hospital.service.NotaVisitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.data.domain.Pageable;

import com.dh.hospital.entity.Paciente;
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
    public ResponseEntity<NotaVisita> show(@PathVariable Long id) {
        Optional<NotaVisita> notaOptional = notaVisitaService.findById(id);
        if (!notaOptional.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(notaOptional.get());
    }

    @PostMapping
    public ResponseEntity<NotaVisita> store(@Valid @RequestBody NotaVisita notaVisita) {
        Optional<Paciente> pacienteOptional = pacienteService.findById(notaVisita.getPaciente().getId());
        if (!pacienteOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        notaVisita.setPaciente(pacienteOptional.get());

        Doctor doctor = doctorService.findById(notaVisita.getDoctor().getId());
        if (doctor == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        notaVisita.setDoctor(doctor);

        notaVisita.setCreatedBy(1);
        NotaVisita notaNew = notaVisitaService.save(notaVisita);

        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(notaNew.getId()).toUri();

        return ResponseEntity.created(ubicacion).body(notaNew);
    }
}
