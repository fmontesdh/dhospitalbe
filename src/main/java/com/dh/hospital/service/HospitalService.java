package com.dh.hospital.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.dh.hospital.entity.Hospital;

public interface HospitalService {

    public abstract Page<Hospital> findAll(Pageable pageable);

    public abstract Optional<Hospital> findById(Long id);

    public abstract Hospital save(Hospital hospital);

    public abstract Boolean update(Long id, Hospital hospital);

    public abstract Boolean delete(Long id);

}
