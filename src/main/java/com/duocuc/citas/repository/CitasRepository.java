package com.duocuc.citas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.duocuc.citas.model.CitaMedica;


public interface CitasRepository extends JpaRepository<CitaMedica, Long>{
}