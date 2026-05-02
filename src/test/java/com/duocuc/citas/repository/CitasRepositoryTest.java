package com.duocuc.citas.repository;

import com.duocuc.citas.model.CitaMedica;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CitasRepositoryTest {

    @Autowired
    private CitasRepository citasRepository;

    @Test
    void testSaveAndFindById() {
        CitaMedica cita = new CitaMedica();
        cita.setPaciente("Test User");
        cita.setDoctor("Dr. Test");
        cita.setEspecialidad("General");
        cita.setFecha(LocalDate.from(LocalDateTime.now()));
        cita.setHora("09:00");
        cita.setEstado("PENDIENTE");

        CitaMedica savedCita = citasRepository.save(cita);
        Optional<CitaMedica> foundCita = citasRepository.findById(savedCita.getId());

        assertTrue(foundCita.isPresent());
        assertEquals("Test User", foundCita.get().getPaciente());
    }
}
