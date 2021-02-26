package com.damidiaz.empleos.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.damidiaz.empleos.model.Vacante;
import com.damidiaz.empleos.service.VacantesService;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	
	@Autowired
	private VacantesService serviceVacantes;
	
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
	
		List<Vacante> listaVacantes = this.serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", listaVacantes);
		
		return "vacantes/listVacantes";
	}
	
	
	
	@GetMapping("/create")
	public String crear() {
		return "vacantes/formVacante";
	}
	
	
//	@PostMapping("/save")
//	public String guardar(@RequestParam("nombre") String nombre,@RequestParam("descripcion") String descripcion,@RequestParam("estatus") 
//	String status,@RequestParam("fecha") String fecha,@RequestParam("destacado") int destacado,@RequestParam("salario") double salario,
//	@RequestParam("detalles") String detalles) {
//		System.out.println("Nombre:" + nombre);
//		System.out.println("Descripcion:" + descripcion);
//		System.out.println("Estatus:" + status);
//		System.out.println("Fecha:" + fecha);
//		System.out.println("Destacado:" + destacado);
//		System.out.println("Salario:" + salario);
//		System.out.println("Detalles:" + detalles);
//		
//		return"vacantes/listVacantes";
//	}

	@PostMapping("/save")
	public String guardar(Vacante vacante) {
		
		this.serviceVacantes.guardar(vacante);
		
		System.out.println("Vacante:" + vacante);
		
		return"vacantes/listVacantes";
	}
	
	
	@GetMapping("/delete")
	public String eliminar(@RequestParam("id") int idVacante,Model model) {
		System.out.println("borrando vacante :" + idVacante);
		model.addAttribute("id", idVacante);
		return "mensaje";
	}
	
	@GetMapping("/view/{id}")
	 public String verDetalle(@PathVariable("id")int idVacante,Model model) {
		
		Vacante vacante = this.serviceVacantes.buscarPorId(idVacante);
		
		System.out.println("id vacante:" + idVacante);
		model.addAttribute("idVacante",idVacante);
		model.addAttribute("vacante",vacante);
		
		return "detalle";
		
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat,false));
		
		
	}
	
	
}
