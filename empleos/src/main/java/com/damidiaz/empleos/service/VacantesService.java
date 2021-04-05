package com.damidiaz.empleos.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.damidiaz.empleos.model.Vacante;

public interface VacantesService {
    List<Vacante> buscarTodas();
    Vacante buscarPorId(Integer id);
    void guardar(Vacante vacante);
    List<Vacante> buscarDestacadas();
    List<Vacante> buscarByExample(Example<Vacante> example);
    void eliminar(Integer id);
    Page<Vacante> buscarTodas(Pageable page);
    
}
