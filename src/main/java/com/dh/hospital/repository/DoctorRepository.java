package com.dh.hospital.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dh.hospital.entity.Doctor;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {

    Collection<Doctor> findAll();

    @Query("select d " +
            "from Doctor d " +
            "inner join fetch d.especialidades  " +
            "where d.id = :id")
    Doctor findWhitEspecialidadesByIdDoctor(@Param("id") Long id);
}
