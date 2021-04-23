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
    /*
     * una consulta del tipo queryByExample ejecuta consultas del tipo select donde las distintas condiciones
     * del where se formaran dinamicamente en base al clase de modelo que pasemos como parametro en este caso Vacante
     * Los filtros del where se formaran dependiendo de los atributos de la clase de modelo que nos sean nulos
     */
    List<Vacante> buscarByExample(Example<Vacante> example);
    void eliminar(Integer id);
    Page<Vacante> buscarTodas(Pageable page);
    
}
