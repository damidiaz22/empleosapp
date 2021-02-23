package com.damidiaz.empleos.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.damidiaz.empleos.model.Vacante;

@Controller
public class HomeController {
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		List <Vacante> lista = getVacante();
		model.addAttribute("vacantes",lista);
		
		return "tabla";
	}	
	public List<Vacante> getVacante(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		List<Vacante> lista = new LinkedList<Vacante>();
		
		try {
			
			Vacante vacante1 = new Vacante();
			vacante1.setId(1);
			vacante1.setNombre("ingeniero de telecos");
			vacante1.setDescripcion("se necesita ingeniero de soporte");
			vacante1.setFecha(sdf.parse("09-22-2020"));
			vacante1.setSalario(9000.0);
			vacante1.setDestacado(1);
			vacante1.setImagen("empresa1.png");
			lista.add(vacante1);
			
			
			Vacante vacante2 = new Vacante();
			vacante2.setId(2);
			vacante2.setNombre("analista de cuentas Jr");
			vacante2.setDescripcion("se necesita analista contable para empresa de transporte");
			vacante2.setFecha(sdf.parse("09-22-2020"));
			vacante2.setSalario(1900.0);
			vacante2.setDestacado(0);
			vacante2.setImagen("empresa2.png");
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
			vacante4.setImagen("empresa3.png");
			lista.add(vacante4);
			
		}catch(Exception e) {
		 System.out.println(e.getMessage());	
		}
		
		return lista;
	}
	
	@GetMapping("/detalle")
	public String mostrarDetalle(Model modelo) {
		Vacante vacante = new Vacante();
		vacante.setNombre("ingeniero de telecos");
		vacante.setDescripcion("se necesita ingeniero de soporte");
		vacante.setFecha(new Date());
		vacante.setSalario(9000.0);
		
		modelo.addAttribute("vacante",vacante);
		
		return "detalle";
		
		
	}
	
	@GetMapping("/listado")
	public String mostrarListado(Model model) {
		List<String> list = new LinkedList<String>();
		list.add("Ingeniero de sistemas");
		list.add("Auxiliar de contabilidad");
		list.add("vendedor");
		list.add("Arquitecto");
		
		model.addAttribute("empleos", list);
		
		return "listado";
	}

	@GetMapping("/")
	public String mostrarHome(Model model) {
		//model.addAttribute("mensaje","Bienvenidos a empleos app");
		//model.addAttribute("fecha",new Date());
		
		String nombre ="Auxiliar de contabilidad";
		Date fechaPub = new Date();
		double salario = 9000.0;
		boolean vigente = true;
		
		model.addAttribute("nombre", nombre);
		model.addAttribute("fecha",fechaPub);
		model.addAttribute("salario",salario);
		model.addAttribute("vigente",vigente);
		
		
		
		return "home";
	}

}
