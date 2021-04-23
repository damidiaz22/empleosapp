package com.damidiaz.empleos.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.damidiaz.empleos.model.Vacante;
import com.damidiaz.empleos.repositorio.VacantesRepository;
import com.damidiaz.empleos.service.VacantesService;

@Service
@Primary
public class VacantesServiceJpa implements VacantesService {

	
	@Autowired
	private VacantesRepository vacantesRepo;
	
	@Override
	public List<Vacante> buscarTodas() {
		return this.vacantesRepo.findAll();
	}

	@Override
	public Vacante buscarPorId(Integer id) {
		Optional<Vacante> optional =this.vacantesRepo.findById(id);

		if(optional.isPresent()) {
			return optional.get();
		}
		
		return null;
	}

	@Override
	public void guardar(Vacante vacante) {
		this.vacantesRepo.save(vacante);

	}

	@Override
	public List<Vacante> buscarDestacadas() {
		return this.vacantesRepo.findByDestacadoAndEstatusOrderByIdDesc(1,"Aprobada");

	}

	@Override
	public void eliminar(Integer id) {
		this.vacantesRepo.deleteById(id);
	}
	
	

	@Override
	public List<Vacante> buscarByExample(Example<Vacante> example) {
		return this.vacantesRepo.findAll(example);
	}

	@Override
	public Page<Vacante> buscarTodas(Pageable page) {
		return this.vacantesRepo.findAll(page);
		
	}

}
