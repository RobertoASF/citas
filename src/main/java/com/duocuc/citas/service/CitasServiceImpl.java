package com.duocuc.citas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duocuc.citas.model.CitaMedica;
import com.duocuc.citas.repository.CitasRepository;

import java.util.List;
import java.util.Optional;


@Service
public class CitasServiceImpl implements CitasService{
    @Autowired
    private CitasRepository citasRepository;

    @Override
    public List<CitaMedica> getAllCitas(){
        return citasRepository.findAll();
    }

    @Override
    public Optional<CitaMedica> getCitaById(Long id){
        return citasRepository.findById(id);
    }

    @Override
    public CitaMedica createCita(CitaMedica citaMedica){
        return citasRepository.save(citaMedica);
    }

    @Override
    public CitaMedica updateCita(Long id, CitaMedica citaMedica){
        if(citasRepository.existsById(id)){
            citaMedica.setId(id);
            return citasRepository.save(citaMedica);
        } else {
            return null;
        }
    }
    @Override
    public void deleteCita(Long id){
        citasRepository.deleteById(id);
    }

}