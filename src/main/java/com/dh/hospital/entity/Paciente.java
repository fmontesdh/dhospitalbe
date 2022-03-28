package com.dh.hospital.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "paciente")
public class Paciente extends Audit {

    @Id
    @Column(name = "paciente_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private String apellido;

    @NotNull
    // @JsonFormat(pattern = "MM/dd/YYYY")
    private Date fechaNacimiento;

    @NotNull
    private String direccion;

    private String fotoPerfilPath;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hospital_id")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Hospital hospital;

    // @JsonBackReference
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private Set<NotaVisita> notasVisita = new HashSet<>();

    public Paciente() {
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void pacienteEdit(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFotoPerfilPath() {
        return fotoPerfilPath;
    }

    public void setFotoPerfilPath(String fotoPerfilPath) {
        this.fotoPerfilPath = fotoPerfilPath;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Set<NotaVisita> getNotasVisita() {
        return notasVisita;
    }

    public void setNotasVisita(Set<NotaVisita> notasVisita) {
        this.notasVisita = notasVisita;
//		for(NotaVisita notaVisita : notasVisita) {
//			notaVisita.setPaciente(this);
//		}
    }
}
