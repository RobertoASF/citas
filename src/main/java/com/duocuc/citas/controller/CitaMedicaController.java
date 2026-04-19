package com.duocuc.citas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.duocuc.citas.model.CitaMedica;
import com.duocuc.citas.service.CitasService;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/citas")
@CrossOrigin(origins = "*")
public class CitaMedicaController {
    @Autowired
    private CitasService citasService;

    @GetMapping
    public List<CitaMedica> getAllCitas() {
        return citasService.getAllCitas();
    }

    @GetMapping("/{id}")
    public Optional<CitaMedica> getCitaById(@PathVariable Long id){
        return citasService.getCitaById(id);
    }
    @PostMapping
    public CitaMedica createCita(@RequestBody CitaMedica citaMedica){
        return citasService.createCita(citaMedica);
    }

    @PutMapping("/{id}")
    public CitaMedica upodateCita(@PathVariable Long id,@RequestBody CitaMedica citaMedica){
        return citasService.updateCita(id, citaMedica);
    }

    @DeleteMapping("/{id}")
    public void deleteCita(@PathVariable Long id){
        citasService.deleteCita(id);
    }
}