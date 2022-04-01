package com.dh.hospital.controller;

import java.util.Collection;

import com.dh.hospital.dto.EspecialidadDto;
import com.dh.hospital.service.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dh.hospital.entity.Especialidad;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/especialidad")
public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;

    @GetMapping
    public ResponseEntity<Collection<Especialidad>> index() {
        return new ResponseEntity<>(especialidadService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadDto> show(@PathVariable long id) {
        EspecialidadDto especialidad = especialidadService.findById(id);
        if (especialidad != null) {
            return new ResponseEntity<>(especialidad, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<EspecialidadDto> store(@RequestBody EspecialidadDto especialidad) {
        return new ResponseEntity<>(especialidadService.save(especialidad), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecialidadDto> update(@PathVariable Long id, @Valid @RequestBody EspecialidadDto especialidadDto) {
        EspecialidadDto pacienteEdit = especialidadService.update(id, especialidadDto);
        if(pacienteEdit != null ){
            return new ResponseEntity<>(pacienteEdit, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable long id) {
        especialidadService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
