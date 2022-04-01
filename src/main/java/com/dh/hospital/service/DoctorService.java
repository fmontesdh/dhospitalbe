package com.dh.hospital.service;

import java.util.Collection;

import com.dh.hospital.dto.DoctorDto;
import com.dh.hospital.entity.Doctor;

public interface DoctorService {

    public abstract Collection<Doctor> findAll();

    public abstract DoctorDto save(DoctorDto doctor);

    public abstract DoctorDto update(Long id, DoctorDto doctor);

    public void delete(Long id);

    public abstract DoctorDto findById(Long id);

    public abstract DoctorDto findDoctorAndHospitalById(Long id);
}
