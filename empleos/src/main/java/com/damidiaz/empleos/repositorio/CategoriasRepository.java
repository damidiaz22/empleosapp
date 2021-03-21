package com.damidiaz.empleos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.damidiaz.empleos.model.Categoria;
//public interface CategoriasRepository extends CrudRepository<Categoria, Integer>
public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {

}
