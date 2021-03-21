package com.damidiaz.empleos.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.damidiaz.empleos.model.Categoria;


@Service
public class CategoriasServiceImpl implements CategoriasService {

	
	private List<Categoria> lista = null;
	
	public CategoriasServiceImpl() {
		
		lista =  new LinkedList<Categoria>();
		
		try {
			
			Categoria categoria1 = new Categoria();
			categoria1.setId(1);
			categoria1.setNombre("Ventas");
			categoria1.setDescripcion("");
			lista.add(categoria1);
			
			Categoria categoria2 = new Categoria();
			categoria2.setId(2);
			categoria2.setNombre("Sistemas");
			categoria2.setDescripcion("");
			lista.add(categoria2);
			
			Categoria categoria3 = new Categoria();
			categoria3.setId(3);
			categoria3.setNombre("Finanzas");
			categoria3.setDescripcion("");
			lista.add(categoria3);
			
			Categoria categoria4 = new Categoria();
			categoria4.setId(4);
			categoria4.setNombre("Bancario");
			categoria4.setDescripcion("");
			lista.add(categoria4);
			
			Categoria categoria5 = new Categoria();
			categoria5.setId(5);
			categoria5.setNombre("Desarrollo de softwere");
			categoria5.setDescripcion("programador");
			lista.add(categoria5);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void guardar(Categoria categoria) {
		this.lista.add(categoria);
		
	}

	@Override
	public List<Categoria> buscarTodas() {
		return lista;
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(Integer idVacante) {
		// TODO Auto-generated method stub
		
	}

}
