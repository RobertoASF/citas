package com.duocuc.citas.controller;

import com.duocuc.citas.model.CitaMedica;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaMedicaController {

    private final List<CitaMedica> citas = new ArrayList<>();

    public CitaMedicaController() {
        // 8 datos mínimos en memoria para el "Completamente Logrado"
        citas.add(new CitaMedica(1, "Pedro Pascal", "Dr. House", "Medicina General", "2025-04-10 09:00", "Programada"));
        citas.add(new CitaMedica(2, "Camila Vergara", "Dra. Grey", "Cirugía", "2025-04-11 11:30", "Cancelada"));
        citas.add(new CitaMedica(3, "Roberto Sánchez", "Dr. Strange", "Neurología", "2025-04-12 15:00", "Programada"));
        citas.add(new CitaMedica(4, "Lorena Pino", "Dra. Polo", "Pediatría", "2025-03-30 10:00", "Realizada"));
        citas.add(new CitaMedica(5, "John Doe", "Dr. Simi", "Traumatologia", "2025-05-14 16:45", "Programada"));
        citas.add(new CitaMedica(6, "Juanita Perez", "Dra. Quinn", "Ginecología", "2025-04-15 08:30", "Programada"));
        citas.add(new CitaMedica(7, "Javier Moya", "Dr. Who", "Medicina General", "2025-04-16 12:00", "Cancelada"));
        citas.add(new CitaMedica(8, "Daniela Ortiz", "Dra. Foster", "Psiquiatría", "2025-04-17 18:00", "Programada"));
    }

    @GetMapping
    public ResponseEntity<List<CitaMedica>> obtenerTodasLasCitas() {
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerCitaPorId(@PathVariable int id) {
        for (CitaMedica cita : citas) {
            if (cita.getId() == id) {
                return ResponseEntity.ok(cita);
            }
        }

        java.util.Map<String, String> respuestaError = new java.util.HashMap<>();
        respuestaError.put("error", "No existe cita médica registrada con el ID: " + id);

        return ResponseEntity.status(org.springframework.http.HttpStatus.NOT_FOUND).body(respuestaError);
    }
}