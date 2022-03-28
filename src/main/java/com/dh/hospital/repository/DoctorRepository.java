package com.dh.hospital.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dh.hospital.entity.Doctor;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    Collection<Doctor> findAll();
}
