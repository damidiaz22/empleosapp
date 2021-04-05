package com.damidiaz.empleos.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damidiaz.empleos.model.Usuario;
import com.damidiaz.empleos.repositorio.UsuariosRepository;
import com.damidiaz.empleos.service.UsuariosService;

@Service
public class UsuarioServiceJpa implements UsuariosService {
	
	
	@Autowired
	private UsuariosRepository usuariosRepo;

	@Override
	public void guardar(Usuario usuario) {
		this.usuariosRepo.save(usuario);
	}

	@Override
	public void eliminar(Integer idUsuario) {
		this.usuariosRepo.deleteById(idUsuario);
	}

	@Override
	public List<Usuario> buscarTodos() {
		return this.usuariosRepo.findAll();
	}

}
