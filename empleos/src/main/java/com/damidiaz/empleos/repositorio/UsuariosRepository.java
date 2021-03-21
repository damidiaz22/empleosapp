package com.damidiaz.empleos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.damidiaz.empleos.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

}
