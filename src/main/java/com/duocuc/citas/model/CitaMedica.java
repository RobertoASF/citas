package com.duocuc.citas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.hateoas.RepresentationModel;


@Entity
@Table(name = "CITAS")
@JsonPropertyOrder({"id", "paciente", "doctor", "especialidad", "fecha_Hora", "estado"})
public class CitaMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "El nombre del paciente es obligatorio")
    @Size(min = 3, max = 100)
    @Column(name = "PACIENTE")
    @JsonProperty("paciente")
    private String paciente;


    @NotBlank(message = "El nombre del doctor es obligatorio")
    @Column(name = "DOCTOR")
    @JsonProperty("doctor")
    private String doctor;

    @NotBlank(message = "La especialidad es obligatoria")
    @Column(name = "ESPECIALIDAD")
    @JsonProperty("especialidad")
    private String especialidad;

    @NotBlank(message = "La fecha y hora son obligatorias")
    @Column(name = "FECHAHORA")
    @JsonProperty("fechaHora")
    private String fechaHora;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "PENDIENTE|COMPLETADA|CANCELADA", message = "Estado no válido") // aqui agregamos una validacion para controlar los datos que entrean
    @Column(name = "ESTADO")
    @JsonProperty("estado")
    private String estado;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPaciente() { return paciente; }
    public void setPaciente(String paciente) { this.paciente = paciente; }

    public String getDoctor() { return doctor; }
    public void setDoctor(String doctor) { this.doctor = doctor; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getFechaHora() { return fechaHora; }
    public void setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}