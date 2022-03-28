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

import com.dh.hospital.entity.Doctor;
import com.dh.hospital.entity.NotaVisita;
import com.dh.hospital.repository.DoctorRepository;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping
    public ResponseEntity<Collection<Doctor>> index() {
        return new ResponseEntity<>(doctorRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> show(@PathVariable long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found id " + id));
        if (doctor != null) {
            return new ResponseEntity<>(doctorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found id " + id)), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> store(@RequestBody Doctor doctor) {
        doctor.setCreatedBy(1);
        return new ResponseEntity<>(doctorRepository.save(doctor), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable long id) {
        doctorRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/{id}/notasvisitas")
    public ResponseEntity<Collection<NotaVisita>> getNotasVisitaOfDoctor(@PathVariable long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(null);
        if (doctor != null) {
            return new ResponseEntity<>(doctor.getNotasVisita(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
