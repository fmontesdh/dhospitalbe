package com.dh.hospital.controller;

import com.dh.hospital.dto.HospitalDto;
import com.dh.hospital.dto.PacienteDto;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<PacienteDto> store(@Valid @RequestBody PacienteDto pacienteDto) {
        Optional<HospitalDto> hospitalOptional = hospitalService.findById(pacienteDto.getHospital().getId());
        if (!hospitalOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        pacienteDto.setHospital(hospitalOptional.get());
        PacienteDto pacienteGuardado = pacienteService.save(pacienteDto);

        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(pacienteGuardado.getId()).toUri();

        return ResponseEntity.created(ubicacion).body(pacienteGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDto> update(@PathVariable Long id, @Valid @RequestBody PacienteDto pacienteDto) {
        PacienteDto pacienteEdit = pacienteService.update(id, pacienteDto);
        if(pacienteEdit != null ){
            return new ResponseEntity<>(pacienteEdit, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Paciente> destroy(@PathVariable Long id) {
        if (pacienteService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> show(@PathVariable Long id) {
        Optional<PacienteDto> pacienteOptional = pacienteService.findById(id);
        if (!pacienteOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(pacienteOptional.get());
    }

}
