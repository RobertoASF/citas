package com.duocuc.citas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.hateoas.RepresentationModel;


@Entity
@Table(name = "citas")
@JsonPropertyOrder()
public class CitaMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "paciente")
    @JsonProperty("paciente")
    private String paciente;


    @NotBlank
    @Column(name = "doctor")
    @JsonProperty("doctor")
    private String doctor;

    @NotBlank
    @Column(name = "especialidad")
    @JsonProperty("especialidad")
    private String especialidad;

    @NotBlank
    @Column(name = "fechaHora")
    @JsonProperty("fechaHora")
    private String fechaHora;

    @NotBlank
    @Column(name = "estado")
    @JsonProperty("estado")
    private String estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}