package com.duocuc.citas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.duocuc.citas.model.CitaMedica;
import com.duocuc.citas.service.CitasService;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/citas")
@CrossOrigin(origins = "*")
public class CitaMedicaController {

    @Autowired
    private CitasService citasService;

    @GetMapping
    public ResponseEntity<List<CitaMedica>> getAllCitas() {
        List<CitaMedica> citas = citasService.getAllCitas();
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaMedica> getCitaById(@PathVariable Long id) {
        return citasService.getCitaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CitaMedica> createCita(@Valid @RequestBody CitaMedica citaMedica) {
        CitaMedica nuevaCita = citasService.createCita(citaMedica);
        return new ResponseEntity<>(nuevaCita, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaMedica> updateCita(@PathVariable Long id, @Valid @RequestBody CitaMedica citaMedica) {
        CitaMedica actualizada = citasService.updateCita(id, citaMedica);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable Long id) {
        if (citasService.getCitaById(id).isPresent()) {
            citasService.deleteCita(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}