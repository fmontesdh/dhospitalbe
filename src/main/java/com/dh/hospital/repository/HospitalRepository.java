package com.dh.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dh.hospital.entity.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {


}
