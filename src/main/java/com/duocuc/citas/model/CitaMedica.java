package com.duocuc.citas.model;

public class CitaMedica {
    private int id;
    private String paciente;
    private String doctor;
    private String especialidad;
    private String fechaHora;
    private String estado;

    public CitaMedica(int id, String paciente, String doctor, String especialidad, String fechaHora, String estado) {
        //agregamos pequeña validación para que la petición cumpla con datos requeridos
        if (id <= 0 || paciente == null || paciente.trim().isEmpty() || fechaHora == null) {
            throw new IllegalArgumentException("Error: Datos de la cita médica no son válidos.");
        }
        this.id = id;
        this.paciente = paciente;
        this.doctor = doctor;
        this.especialidad = especialidad;
        this.fechaHora = fechaHora;
        this.estado = estado;
    }

    public int getId() { return id; }
    public String getPaciente() { return paciente; }
    public String getDoctor() { return doctor; }
    public String getEspecialidad() { return especialidad; }
    public String getFechaHora() { return fechaHora; }
    public String getEstado() { return estado; }
}