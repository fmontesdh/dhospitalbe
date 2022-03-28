package com.dh.hospital.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
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
import com.dh.hospital.service.HospitalService;

@RestController
@RequestMapping("/api/hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @GetMapping
    public ResponseEntity<Page<Hospital>> index(Pageable pageable) {
        return ResponseEntity.ok(hospitalService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Hospital> store(@Valid @RequestBody Hospital hospital) {
        hospital.setCreatedBy(1);
        Hospital hospitalGuardado = hospitalService.save(hospital);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(hospitalGuardado.getId()).toUri();
        return ResponseEntity.created(ubicacion).body(hospitalGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hospital> update(@PathVariable Long id, @Valid @RequestBody Hospital hospital) {
        if (hospitalService.update(id, hospital)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Hospital> destroy(@PathVariable Long id) {
        if (hospitalService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospital> show(@PathVariable Long id) {
        Optional<Hospital> hospitalOptional = hospitalService.findById(id);
        if (!hospitalOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(hospitalOptional.get());
    }
}
