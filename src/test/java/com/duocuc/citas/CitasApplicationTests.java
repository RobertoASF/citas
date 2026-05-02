package com.duocuc.citas;

import com.duocuc.citas.controller.CitaMedicaController;
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

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CitasApplicationTests {

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
	void testObtenerCitas() throws Exception {
		CitaMedica cita = new CitaMedica();
		cita.setId(1L);
		cita.setPaciente("Roberto");
		cita.setDoctor("Dr. House");
		cita.setEspecialidad("Medicina General");
		cita.setEstado("PENDIENTE");

		when(citasService.getAllCitas()).thenReturn(Arrays.asList(cita));

		mockMvc.perform(get("/citas")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].estado").value("PENDIENTE"));
	}

	@Test
	void testCrearCita() throws Exception {
		CitaMedica cita = new CitaMedica();
		cita.setId(2L);
		cita.setPaciente("Roberto");
		cita.setDoctor("Dr. House");
		cita.setEspecialidad("Medicina General");
		cita.setEstado("PENDIENTE");

		when(citasService.createCita(any(CitaMedica.class))).thenReturn(cita);

		mockMvc.perform(post("/citas")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"paciente\":\"Roberto\",\"doctor\":\"Dr. House\",\"especialidad\":\"Medicina General\",\"fecha\":\"2026-05-02\",\"hora\":\"08:00\",\"estado\":\"PENDIENTE\"}"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value(2));
	}
}
