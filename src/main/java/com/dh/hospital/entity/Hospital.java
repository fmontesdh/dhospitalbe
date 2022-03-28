package com.dh.hospital.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "hospital", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre"})})
public class Hospital extends Audit {

    @Id
    @Column(name = "hospital_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;

    public Hospital() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Hospital [id=" + id + ", nombre=" + nombre + ", getCreatedAt()=" + getCreatedAt() + ", getUpdatedAt()="
                + getUpdatedAt() + ", getCreatedBy()=" + getCreatedBy() + ", getUpdatedBy()=" + getUpdatedBy() + "]";
    }

}
