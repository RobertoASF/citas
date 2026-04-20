package com.duocuc.citas.controller;

import com.duocuc.citas.model.CitaMedica;
import com.duocuc.citas.service.CitasService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/citas")
@CrossOrigin(origins = "*")
public class CitaMedicaController {

    private static final Logger log = LoggerFactory.getLogger(CitaMedicaController.class);

    @Autowired
    private CitasService citasService;

    @GetMapping
    public ResponseEntity<List<CitaMedica>> getAllCitas() {
        List<CitaMedica> citas = citasService.getAllCitas();
        log.info("GET /citas");
        log.info("Retornando todas las citas medicas registradas");
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaMedica> getCitaById(@PathVariable Long id) {
        log.info("Iniciando búsqueda de cita con ID: {}", id);

        return citasService.getCitaById(id)
                .map(cita -> {
                    log.info("Cita encontrada exitosamente para el paciente: {}", cita.getPaciente());
                    return ResponseEntity.ok(cita);
                })
                .orElseGet(() -> {
                    log.warn("No se encontró ninguna cita con el ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public ResponseEntity<CitaMedica> createCita(@Valid @RequestBody CitaMedica citaMedica) {
        log.info("Recibida solicitud para crear cita para el paciente: {}", citaMedica.getPaciente());
        CitaMedica nuevaCita = citasService.createCita(citaMedica);
        log.info("Cita creada exitosamente con ID: {}", nuevaCita.getId());
        return new ResponseEntity<>(nuevaCita, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaMedica> updateCita(@PathVariable Long id, @Valid @RequestBody CitaMedica citaMedica) {
        log.info("Iniciando actualización de la cita con ID: {}", id);
        CitaMedica actualizada = citasService.updateCita(id, citaMedica);

        if (actualizada != null) {
            log.info("Cita con ID: {} actualizada correctamente", id);
            return ResponseEntity.ok(actualizada);
        } else {
            log.warn("No se pudo actualizar: La cita con ID: {} no existe", id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable Long id) {
        log.info("Solicitud para eliminar la cita con ID: {}", id);

        if (citasService.getCitaById(id).isPresent()) {
            citasService.deleteCita(id);
            log.info("Cita con ID: {} eliminada exitosamente", id);
            return ResponseEntity.noContent().build();
        } else {
            log.warn("Fallo al eliminar: No se encontró la cita con ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/disponibilidad")
    public ResponseEntity<Boolean> consultarDisponibilidad(
            @RequestParam LocalDate fecha,
            @RequestParam String hora) {
        log.info("Consultando disponibilidad para fecha: {} y hora: {}", fecha, hora);
        boolean disponible = citasService.verificarDisponibilidad(fecha, hora);
        return ResponseEntity.ok(disponible);
    }

}