package com.duocuc.citas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.duocuc.citas.model.CitaMedica;

import java.time.LocalDate;


public interface CitasRepository extends JpaRepository<CitaMedica, Long>{

    boolean existsByFechaAndHora(LocalDate fecha, String hora);}

