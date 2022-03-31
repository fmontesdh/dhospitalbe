package com.dh.hospital.service;

import java.util.Collection;
import java.util.Optional;

import com.dh.hospital.dto.converter.DoctorConverter;
import com.dh.hospital.dto.response.DoctorDto;
import com.dh.hospital.entity.Especialidad;
import com.dh.hospital.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dh.hospital.entity.Doctor;
import com.dh.hospital.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorConverter doctorConverter;

    @Override
    public Collection<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor update(Long id, Doctor doctor) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if (doctorOptional.isPresent()) {
            Doctor doctorEdit = doctorOptional.get();
            doctorEdit.setNombre(doctor.getNombre());
            doctorEdit.setApellido(doctor.getApellido());
            doctorEdit.setFechaNacimiento(doctor.getFechaNacimiento());
            doctorEdit.setDireccion(doctor.getDireccion());
            doctorEdit.setHospital(doctor.getHospital());
            doctorEdit.setUpdatedBy(doctor.getUpdatedBy());
            return doctorRepository.save(doctorEdit);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public Doctor findById(Long id) {
        return doctorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found id " + id));
    }

    @Override
    public DoctorDto findDoctorAndHospitalById(Long id) {
        Doctor doctor = doctorRepository.findWhitEspecialidadesByIdDoctor(id);
        return doctorConverter.entityToDto(doctor);
    }
}
