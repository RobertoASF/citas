package com.duocuc.citas.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class CitaMedicaTest {

    @Test
    void testGettersAndSetters() {
        CitaMedica cita = new CitaMedica();
        LocalDate fecha = LocalDate.now();

        cita.setId(1L);
        cita.setPaciente("Roberto");
        cita.setDoctor("Dr. House");
        cita.setEspecialidad("Medicina General");
        cita.setFecha(fecha);
        cita.setHora("08:00");
        cita.setEstado("PROGRAMADA");

        assertAll(
                () -> assertEquals(1L, cita.getId()),
                () -> assertEquals("Roberto", cita.getPaciente()),
                () -> assertEquals("Dr. House", cita.getDoctor()),
                () -> assertEquals("Medicina General", cita.getEspecialidad()),
                () -> assertEquals(fecha, cita.getFecha()),
                () -> assertEquals("08:00", cita.getHora()),
                () -> assertEquals("PROGRAMADA", cita.getEstado())
        );
    }
}