package com.damidiaz.empleos.service;

import java.util.List;

import com.damidiaz.empleos.model.Categoria;

public interface CategoriasService {
	void guardar(Categoria categoria);
	List<Categoria> buscarTodas();
	Categoria buscarPorId(Integer idCategoria);
	void eliminar (Integer idCategoria);
}

