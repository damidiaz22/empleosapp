package com.damidiaz.empleos.service;

import java.util.List;

import com.damidiaz.empleos.model.Vacante;

public interface VacantesService {
    List<Vacante> buscarTodas();
    Vacante buscarPorId(Integer id);
}