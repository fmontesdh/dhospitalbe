package com.dh.hospital.service;

import java.util.Collection;

import com.dh.hospital.entity.Doctor;

public interface DoctorService {

    public abstract Collection<Doctor> findAll();

    public abstract Doctor save(Doctor doctor);

    public abstract Doctor update(Long id, Doctor doctor);

    public void delete(Long id);

    public abstract Doctor findById(Long id);
}
