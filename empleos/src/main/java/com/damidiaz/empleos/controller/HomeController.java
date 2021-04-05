package com.damidiaz.empleos.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.damidiaz.empleos.model.Perfil;
import com.damidiaz.empleos.model.Usuario;
import com.damidiaz.empleos.model.Vacante;
import com.damidiaz.empleos.service.CategoriasService;
import com.damidiaz.empleos.service.UsuariosService;
import com.damidiaz.empleos.service.VacantesService;

@Controller
public class HomeController {
	
	@Autowired
	private CategoriasService serviceCategorias;
	
	@Autowired
	private VacantesService serviceVacantes;
	
	@Autowired
   	private UsuariosService serviceUsuarios;
	
	
	
	@GetMapping("/search")
	public String buscar(@ModelAttribute("search") Vacante vacante,Model model) {
		
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("descripcion",ExampleMatcher.GenericPropertyMatchers.contains());
		
		Example <Vacante> example = Example.of(vacante,matcher);
		List <Vacante> lista = this.serviceVacantes.buscarByExample(example);
		model.addAttribute("vacantes",lista);
		
		System.out.println(vacante);
		
		
		return "home";
		
	}
	
	@GetMapping("/")
	public String mostrarHome(Model model) {
		return "home";
	}
	
	@GetMapping("/signup")
	public String registrarse(Usuario usuario) {
		return "formRegistro";
	}
	
	
	@PostMapping("/signup")
	public String guardarRegistro(Usuario usuario,BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {

			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error:" + error.getDefaultMessage());
			}
			
			return "formRegistro";
		}
		
		usuario.setEstatus(1);
		usuario.setFechaRegistro(new Date());
		
		Perfil perfil = new Perfil();
		perfil.setId(1);
		usuario.agregar(perfil);
		
		this.serviceUsuarios.guardar(usuario);
		attributes.addFlashAttribute("msj", "Registro guardado");
		
		return "redirect:/usuarios/index";
	}
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		List<Vacante> lista = serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", lista);
		
		return "tabla";
	}
	
	@GetMapping("/detalle")
	public String mostrarDetalle(Model model) {
		Vacante vacante = new Vacante();
		vacante.setNombre("Ingeniero de comunicaciones");
		vacante.setDescripcion("Se solicita ingeniero para dar soporte a intranet");
		vacante.setFecha(new Date());
		vacante.setSalario(9700.0);
		model.addAttribute("vacante", vacante);
		return "detalle";
	}
	
	@GetMapping("/listado")
	public String mostrarListado(Model model) {
		List<String> lista = new LinkedList<String>();
		lista.add("Ingeniero  de Sistemas");
		lista.add("Auxiliar de Contabilidad");
		lista.add("Vendedor");
		lista.add("Arquitecto");
		
		model.addAttribute("empleos", lista);
		
		return "listado";
	}
	
	@ModelAttribute
	public void setGenericos(Model model) {
		Vacante vacanteSearch = new Vacante();
		vacanteSearch.reset();
		model.addAttribute("search",vacanteSearch);
		model.addAttribute("vacantes", serviceVacantes.buscarDestacadas());
		model.addAttribute("categorias", serviceCategorias.buscarTodas());
	}
	
	
	/*
	 * Init Binder para string si los detecta vacios en el Data Binding los seea a NULL
	 */
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class,new StringTrimmerEditor(true));
	}
	
}
