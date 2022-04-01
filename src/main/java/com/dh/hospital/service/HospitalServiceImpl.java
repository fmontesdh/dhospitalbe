package com.dh.hospital.service;

import java.util.Optional;

import com.dh.hospital.dto.HospitalDto;
import com.dh.hospital.dto.converter.HospitalConverter;
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

    @Autowired
    private HospitalConverter hospitalConverter;

    @Override
    public Page<Hospital> findAll(Pageable pageable) {
        return hospitalRepository.findAll(pageable);
    }

    @Override
    public Optional<HospitalDto> findById(Long id) {
        HospitalDto hospitalDto = hospitalConverter.entityToDto(hospitalRepository.findById(id).get());
        Optional<HospitalDto> optDto = Optional.of(hospitalDto);
        return optDto;
    }

    @Override
    public HospitalDto save(HospitalDto hospitalDto) {
        Hospital hospital = hospitalConverter.dtoToEntity(hospitalDto);
        hospital.setCreatedBy(1);
        return hospitalConverter.entityToDto(hospitalRepository.save(hospital));
    }

    @Override
    public Boolean update(Long id, HospitalDto hospitalDto) {
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(id);
        if (hospitalOptional.isPresent()) {
            hospitalOptional.get().setNombre(hospitalDto.getNombre());
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
