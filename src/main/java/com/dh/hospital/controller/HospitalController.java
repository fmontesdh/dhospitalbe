package com.dh.hospital.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import com.dh.hospital.dto.HospitalDto;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<HospitalDto> store(@Valid @RequestBody HospitalDto hospital) {
        HospitalDto hospitalGuardado = hospitalService.save(hospital);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(hospitalGuardado.getId()).toUri();
        return ResponseEntity.created(ubicacion).body(hospitalGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HospitalDto> update(@PathVariable Long id, @Valid @RequestBody HospitalDto hospital) {
        if (hospitalService.update(id, hospital)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HospitalDto> destroy(@PathVariable Long id) {
        if (hospitalService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalDto> show(@PathVariable Long id) {
        Optional<HospitalDto> hospitalOptional = hospitalService.findById(id);
        if (!hospitalOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(hospitalOptional.get());
    }
}
