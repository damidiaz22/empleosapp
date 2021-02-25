package com.damidiaz.empleos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.damidiaz.empleos.model.Vacante;
import com.damidiaz.empleos.service.VacantesService;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	
	@Autowired
	private VacantesService serviceVacantes;
	

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
}
