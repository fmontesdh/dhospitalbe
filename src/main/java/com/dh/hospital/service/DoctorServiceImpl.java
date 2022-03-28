package com.dh.hospital.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dh.hospital.entity.Doctor;
import com.dh.hospital.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Collection<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor save(Doctor doctor) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public Doctor findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

}
