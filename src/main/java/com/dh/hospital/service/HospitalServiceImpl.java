package com.dh.hospital.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dh.hospital.entity.Hospital;
import com.dh.hospital.repository.HospitalRepository;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public Page<Hospital> findAll(Pageable pageable) {
        return hospitalRepository.findAll(pageable);
    }

    @Override
    public Optional<Hospital> findById(Long id) {
        return hospitalRepository.findById(id);
    }

    @Override
    public Hospital save(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    @Override
    public Boolean update(Long id, Hospital hospital) {
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(id);
        if (hospitalOptional.isPresent()) {
            hospitalOptional.get().setNombre(hospital.getNombre());
            hospitalOptional.get().setUpdatedBy(1);
            hospitalRepository.save(hospitalOptional.get());
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(id);
        if (hospitalOptional.isPresent()) {
            hospitalRepository.delete(hospitalOptional.get());
            return true;
        }
        return false;
    }

}
