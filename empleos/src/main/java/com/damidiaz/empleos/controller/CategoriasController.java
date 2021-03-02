package com.damidiaz.empleos.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.damidiaz.empleos.model.Categoria;
import com.damidiaz.empleos.service.CategoriasService;

@Controller
@RequestMapping(value="/categorias")
public class CategoriasController {

	
	
	@Autowired
	private CategoriasService serviceCategorias;
	
	
	
	
	@GetMapping("/index")
	//@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String mostrarIndex(Model model) {
		
		
		List<Categoria> listaCategorias = this.serviceCategorias.buscarTodas();
		model.addAttribute("categorias",listaCategorias);
		
		
		return "categorias/listCategorias";
	}

	 @GetMapping("/create")
	//@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String crear() {
		 
		return "categorias/formCategoria";
	}

//	 //@PostMapping("/save")
//	@RequestMapping(value = "/save", method = RequestMethod.POST)
//	public String guardar(@RequestParam("nombre") String nombre,@RequestParam("descripcion") String descripcion) {
//		System.out.println("Categoria:" + nombre );
//		System.out.println("descripcion:" + descripcion );
//		return "categorias/listCategorias";
//	}
	 
	 
	@PostMapping("/save")
	public String guardar(Categoria categoria,BindingResult result,RedirectAttributes attributes) {

		if(result.hasErrors()) {
			
			for(ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error:" + error.getDefaultMessage());
			}
			
			return "categoria/formCategorias";
		}
		
		this.serviceCategorias.guardar(categoria);
		attributes.addFlashAttribute("msj","Registro guardado");
		
		return "redirect:/categorias/index";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat,false));
		
		
	}
	
}
