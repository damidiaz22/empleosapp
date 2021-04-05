package com.damidiaz.empleos.service;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.damidiaz.empleos.model.Vacante;

@Service
public class VacantesServiceImpl  implements VacantesService {
 
	private List<Vacante> lista = null;
	
	public VacantesServiceImpl() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		lista = new LinkedList<Vacante>();
		
		try {
			
			Vacante vacante1 = new Vacante();
			vacante1.setId(1);
			vacante1.setNombre("ingeniero de telecos");
			vacante1.setDescripcion("se necesita ingeniero de soporte");
			vacante1.setFecha(sdf.parse("09-22-2020"));
			vacante1.setSalario(13000.0);
			vacante1.setDestacado(1);
			vacante1.setImagen("logo1.png");
			lista.add(vacante1);
			
			
			Vacante vacante2 = new Vacante();
			vacante2.setId(2);
			vacante2.setNombre("analista de cuentas Jr");
			vacante2.setDescripcion("se necesita analista contable para empresa de transporte");
			vacante2.setFecha(sdf.parse("09-22-2020"));
			vacante2.setSalario(1900.0);
			vacante2.setDestacado(0);
			vacante2.setImagen("logo2.png");
			lista.add(vacante2);
			
			Vacante vacante3 = new Vacante();
			vacante3.setId(3);
			vacante3.setNombre("ingeniero Electrisista");
			vacante3.setDescripcion("se necesita ingeniero electrisita");
			vacante3.setFecha(sdf.parse("09-22-2020"));
			vacante3.setSalario(8000.0);
			vacante3.setDestacado(0);
			lista.add(vacante3);
			
			Vacante vacante4 = new Vacante();
			vacante4.setId(4);
			vacante4.setNombre("arquitecto");
			vacante4.setDescripcion("se necesita arquitecto");
			vacante4.setFecha(sdf.parse("09-22-2020"));
			vacante4.setSalario(9000.0);
			vacante4.setDestacado(1);
			vacante4.setImagen("logo3.png");
			lista.add(vacante4);
			
		}catch(Exception e) {
		 System.out.println(e.getMessage());	
		}
		
	}
	
	@Override
	public List<Vacante> buscarTodas() {
		return lista;
	}

	@Override
	public Vacante buscarPorId(Integer idVacante) {
		
		for(Vacante v: lista) {
			if(v.getId() == idVacante) {
				return v;
			}
		}
		
		return null;
	}

	@Override
	public void guardar(Vacante vacante) {
		lista.add(vacante);
	}

	@Override
	public List<Vacante> buscarDestacadas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Vacante> buscarByExample(Example<Vacante> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Vacante> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

}
