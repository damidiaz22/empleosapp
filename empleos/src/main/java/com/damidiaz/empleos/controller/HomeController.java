package com.damidiaz.empleos.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.damidiaz.empleos.model.Vacante;
import com.damidiaz.empleos.service.VacantesService;

@Controller
public class HomeController {
	
	@Autowired
	private VacantesService serviceVacantes;
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		List <Vacante> lista = serviceVacantes.buscarTodas();
		model.addAttribute("vacantes",lista);
		
		return "tabla";
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
		List <Vacante> lista = serviceVacantes.buscarTodas();
		model.addAttribute("vacantes",lista);
		
		return "home";
	}

}
