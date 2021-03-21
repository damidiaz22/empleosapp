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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.damidiaz.empleos.model.Categoria;
import com.damidiaz.empleos.model.Vacante;
import com.damidiaz.empleos.service.CategoriasService;

@Controller
@RequestMapping(value="/categorias")
public class CategoriasController {

	
	
	@Autowired
	/*
	 * la anotacion @Qualifier sirve para decirle a spring cual implementacion del servicio CategoriasService se debe utilizar
	 * en esta clase,en este caso la implementacion que va a ser inyectada es CsategoriasServiceJpa
	 * 
	 */
	//@Qualifier("categoriasServiceJpa")
	private CategoriasService serviceCategorias;
	
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idCategoria,Model model) {
		Categoria categoria = this.serviceCategorias.buscarPorId(idCategoria);
		model.addAttribute("categoria",categoria);
		return "categorias/formCategoria";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idCategoria,RedirectAttributes attributes) {
		this.serviceCategorias.eliminar(idCategoria);
		attributes.addFlashAttribute("msj","La categoria fue eliminada!");
		return "redirect:/categorias/index";
	}
	
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
		return "redirect:/categorias/index";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat,false));
		
		
	}
	
}
