package com.dh.hospital.controller;

import java.util.Collection;

import com.dh.hospital.dto.DoctorDto;
import com.dh.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dh.hospital.entity.Doctor;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public ResponseEntity<Collection<Doctor>> index() {
        return new ResponseEntity<>(doctorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> show(@PathVariable long id) {
        DoctorDto doctor = doctorService.findById(id);
        if (doctor != null) {
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/especialidades/{id}")
    public ResponseEntity<DoctorDto> findDoctorHospitalById(@PathVariable long id) {
        DoctorDto doctorDto = doctorService.findDoctorAndHospitalById(id);
        if (doctorDto != null) {
            return new ResponseEntity<>(doctorDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> store(@RequestBody DoctorDto doctor) {
        return new ResponseEntity<>(doctorService.save(doctor), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDto> update(@PathVariable Long id, @Valid @RequestBody DoctorDto doctor) {
        DoctorDto doctorEdit = doctorService.update(id, doctor);
        if(doctorEdit != null ){
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable long id) {
        doctorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
