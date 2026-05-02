package com.duocuc.citas.controller;

import com.duocuc.citas.model.CitaMedica;
import com.duocuc.citas.service.CitasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CitaMedicaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CitasService citasService;

    @InjectMocks
    private CitaMedicaController citaMedicaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(citaMedicaController).build();
    }

    @Test
    void getAllCitasReturnsOk() throws Exception {
        when(citasService.getAllCitas()).thenReturn(List.of(buildCita(1L, "PENDIENTE")));

        mockMvc.perform(get("/citas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].estado").value("PENDIENTE"));
    }

    @Test
    void getCitaByIdReturnsOkWhenFound() throws Exception {
        when(citasService.getCitaById(1L)).thenReturn(Optional.of(buildCita(1L, "PENDIENTE")));

        mockMvc.perform(get("/citas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.paciente").value("Roberto"));
    }

    @Test
    void getCitaByIdReturnsNotFoundWhenMissing() throws Exception {
        when(citasService.getCitaById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/citas/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createCitaReturnsCreated() throws Exception {
        when(citasService.createCita(any(CitaMedica.class))).thenReturn(buildCita(2L, "PENDIENTE"));

        mockMvc.perform(post("/citas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validCitaJson("PENDIENTE")))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.doctor").value("Dr. House"));
    }

    @Test
    void updateCitaReturnsOkWhenUpdated() throws Exception {
        when(citasService.updateCita(eq(1L), any(CitaMedica.class))).thenReturn(buildCita(1L, "COMPLETADA"));

        mockMvc.perform(put("/citas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validCitaJson("COMPLETADA")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("COMPLETADA"));
    }

    @Test
    void updateCitaReturnsNotFoundWhenMissing() throws Exception {
        when(citasService.updateCita(eq(99L), any(CitaMedica.class))).thenReturn(null);

        mockMvc.perform(put("/citas/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validCitaJson("PENDIENTE")))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteCitaReturnsNoContentWhenFound() throws Exception {
        when(citasService.getCitaById(1L)).thenReturn(Optional.of(buildCita(1L, "PENDIENTE")));

        mockMvc.perform(delete("/citas/1"))
                .andExpect(status().isNoContent());

        verify(citasService).deleteCita(1L);
    }

    @Test
    void deleteCitaReturnsNotFoundWhenMissing() throws Exception {
        when(citasService.getCitaById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/citas/99"))
                .andExpect(status().isNotFound());

        verify(citasService, never()).deleteCita(99L);
    }

    @Test
    void consultarDisponibilidadReturnsBoolean() throws Exception {
        when(citasService.verificarDisponibilidad(LocalDate.of(2026, 5, 2), "08:00")).thenReturn(true);

        mockMvc.perform(get("/citas/disponibilidad")
                        .param("fecha", "2026-05-02")
                        .param("hora", "08:00"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    private static CitaMedica buildCita(Long id, String estado) {
        CitaMedica cita = new CitaMedica();
        cita.setId(id);
        cita.setPaciente("Roberto");
        cita.setDoctor("Dr. House");
        cita.setEspecialidad("Medicina General");
        cita.setFecha(LocalDate.of(2026, 5, 2));
        cita.setHora("08:00");
        cita.setEstado(estado);
        return cita;
    }

    private static String validCitaJson(String estado) {
        return """
                {
                  "paciente": "Roberto",
                  "doctor": "Dr. House",
                  "especialidad": "Medicina General",
                  "fecha": "2026-05-02",
                  "hora": "08:00",
                  "estado": "%s"
                }
                """.formatted(estado);
    }
}
