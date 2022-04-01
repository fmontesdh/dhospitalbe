package com.dh.hospital.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "especialidad")
public class Especialidad extends Audit {

    @Id
    @Column(name = "especialidad_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private String descripcion;

    private String avatarPath;

    @ManyToMany
    @JsonBackReference
    @JoinTable(name = "doctor_especialidad",
            joinColumns = @JoinColumn(name = "especialidad_id", referencedColumnName = "especialidad_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id"))
    private Set<Doctor> doctores = new HashSet<>();

    public Especialidad() {
    }

    public Especialidad(Long id) {
        this.id = id;
    }

    public Especialidad(Long id, @NotNull String nombre, @NotNull String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public Set<Doctor> getDoctores() {
        return doctores;
    }

    public void setDoctores(Set<Doctor> doctores) {
        this.doctores = doctores;
    }

    @Override
    public String toString() {
        return "Especialidad{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                '}';
    }
}
