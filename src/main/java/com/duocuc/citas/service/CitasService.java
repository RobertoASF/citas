package com.duocuc.citas.service;
import com.duocuc.citas.model.CitaMedica;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface  CitasService {
    List<CitaMedica> getAllCitas();
    Optional<CitaMedica> getCitaById(Long id);
    CitaMedica createCita(CitaMedica CitaMedica);
    CitaMedica updateCita(Long id,CitaMedica citaMedica);
    boolean verificarDisponibilidad(LocalDate fecha, String hora);
    void deleteCita(Long id);
}
