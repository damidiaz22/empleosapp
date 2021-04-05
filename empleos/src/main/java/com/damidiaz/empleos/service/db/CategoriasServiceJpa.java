package com.damidiaz.empleos.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.damidiaz.empleos.model.Categoria;
import com.damidiaz.empleos.repositorio.CategoriasRepository;
import com.damidiaz.empleos.service.CategoriasService;

@Service
/*
 * ya que hay dos clases de servicio que implementan la interfaz CategoriasService(CategoriasServiceJpa y CategoriasServiceImpl)
 * es necesario marcar algunas de las dos implementacion de la interfas con la notacion @Primary,de este modo no habra conflicto al momento de
 * cargar el contxto de spring.
 */
@Primary
public class CategoriasServiceJpa implements CategoriasService {

	@Autowired
	private CategoriasRepository categoriasRepo;
	
	@Override
	public void guardar(Categoria categoria) {
		this.categoriasRepo.save(categoria);
	}

	@Override
	public List<Categoria> buscarTodas() {
		return this.categoriasRepo.findAll();
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		Optional<Categoria> opcional=this.categoriasRepo.findById(idCategoria);
		
		if(opcional.isPresent()) {
			return opcional.get();
		}
		
		return null;
	}

	@Override
	public void eliminar(Integer idCategoria) {
		this.categoriasRepo.deleteById(idCategoria);
	}

	@Override
	public Page<Categoria> buscarTodas(Pageable page) {
		return this.categoriasRepo.findAll(page);
	}

}
