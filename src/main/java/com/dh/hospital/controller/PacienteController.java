package com.dh.hospital.controller;

import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.dh.hospital.entity.Hospital;
import com.dh.hospital.entity.Paciente;
import com.dh.hospital.service.HospitalService;
import com.dh.hospital.service.PacienteService;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private HospitalService hospitalService;

    @GetMapping
    public ResponseEntity<Page<Paciente>> index(Pageable pageable) {
        return ResponseEntity.ok(pacienteService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Paciente> store(@Valid @RequestBody Paciente paciente) {
        Optional<Hospital> hospitalOptional = hospitalService.findById(paciente.getHospital().getId());
        if (!hospitalOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        paciente.setHospital(hospitalOptional.get());
        paciente.setCreatedBy(1);
        Paciente pacienteGuardado = pacienteService.save(paciente);

        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(pacienteGuardado.getId()).toUri();

        return ResponseEntity.created(ubicacion).body(pacienteGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> update(@PathVariable Long id, @Valid @RequestBody Paciente paciente) {
        Optional<Hospital> hospitalOptional = hospitalService.findById(paciente.getHospital().getId());
        if (!hospitalOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        paciente.setHospital(hospitalOptional.get());
        paciente.setUpdatedBy(1);

        if (pacienteService.update(id, paciente)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.unprocessableEntity().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Paciente> destroy(@PathVariable Long id) {
        if (pacienteService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> show(@PathVariable Long id) {
        Optional<Paciente> pacienteOptional = pacienteService.findById(id);
        if (!pacienteOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(pacienteOptional.get());
    }

}
