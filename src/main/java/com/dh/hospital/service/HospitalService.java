package com.dh.hospital.service;

import java.util.Optional;

import com.dh.hospital.dto.HospitalDto;
import com.dh.hospital.entity.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HospitalService {

    public abstract Page<Hospital> findAll(Pageable pageable);

    public abstract Optional<HospitalDto> findById(Long id);

    public abstract HospitalDto save(HospitalDto hospital);

    public abstract Boolean update(Long id, HospitalDto hospital);

    public abstract Boolean delete(Long id);

}
