package com.dh.hospital.controller;

import java.util.Collection;

import com.dh.hospital.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dh.hospital.entity.Especialidad;
import com.dh.hospital.repository.EspecialidadRepository;

@RestController
@RequestMapping("/api/especialidad")
public class EspecialidadController {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @GetMapping
    public ResponseEntity<Collection<Especialidad>> index() {
        return new ResponseEntity<>(especialidadRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidad> show(@PathVariable long id) {
        Especialidad especialidad = especialidadRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found id " + id));
        if (especialidad != null) {
            return new ResponseEntity<>(especialidadRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found id " + id)), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> store(@RequestBody Especialidad especialidad) {
        especialidad.setCreatedBy(1);
        return new ResponseEntity<>(especialidadRepository.save(especialidad), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable long id) {
        especialidadRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
