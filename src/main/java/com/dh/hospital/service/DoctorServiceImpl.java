package com.dh.hospital.service;

import java.util.Collection;
import java.util.Optional;

import com.dh.hospital.dto.EspecialidadDto;
import com.dh.hospital.dto.converter.DoctorConverter;
import com.dh.hospital.dto.DoctorDto;
import com.dh.hospital.entity.Especialidad;
import com.dh.hospital.entity.Hospital;
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
    public DoctorDto save(DoctorDto doctorDto) {
        Doctor doctor = doctorConverter.dtoToEntity(doctorDto);
        doctor.setCreatedBy(1);
        return doctorConverter.entityToDto(doctorRepository.save(doctor));
    }

    @Override
    public DoctorDto update(Long id, DoctorDto doctorDto) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if (doctorOptional.isPresent()) {
            Doctor doctorEdit = doctorOptional.get();
            doctorEdit.setNombre(doctorDto.getNombre());
            doctorEdit.setApellido(doctorDto.getApellido());
            doctorEdit.setFechaNacimiento(doctorDto.getFechaNacimiento());
            doctorEdit.setDireccion(doctorDto.getDireccion());
            doctorEdit.setHospital(new Hospital(doctorDto.getHospital().getId()));
            doctorEdit.getEspecialidades().clear();
            for(EspecialidadDto espDto: doctorDto.getEspecialidades()){
                doctorEdit.getEspecialidades().add(new Especialidad(espDto.getId()));
            }
            doctorEdit.setUpdatedBy(1);
            return doctorConverter.entityToDto(doctorRepository.save(doctorEdit));
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public DoctorDto findById(Long id) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        Optional<DoctorDto> doctorDtoOptional = Optional.of(doctorConverter.entityToDto(doctorOptional.get()));
        return doctorDtoOptional.orElseThrow(()->new ResourceNotFoundException("Not found id " + id));
    }

    @Override
    public DoctorDto findDoctorAndHospitalById(Long id) {
        Doctor doctor = doctorRepository.findWhitEspecialidadesByIdDoctor(id);
        return doctorConverter.entityToDto(doctor);
    }
}
