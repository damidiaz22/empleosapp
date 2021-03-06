package com.damidiaz.empleos.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.damidiaz.empleos.model.Vacante;
import com.damidiaz.empleos.service.CategoriasService;
import com.damidiaz.empleos.service.VacantesService;
import com.damidiaz.empleos.util.Utileria;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {

//	@Value("${empleosapp.ruta.imagenes}")
//	private String ruta;

	@Autowired
	private VacantesService serviceVacantes;

	@Autowired
	/*
	 * la anotacion @Qualifier sirve para decirle a spring cual implementacion del
	 * servicio CategoriasService se debe utilizar en esta clase,en este caso la
	 * implementacion que va a ser inyectada es CsategoriasServiceJpa
	 * 
	 */
	// @Qualifier("categoriasServiceJpa")
	private CategoriasService serviceCategorias;

	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Vacante> listaVacantes = this.serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", listaVacantes);
		return "vacantes/listVacantes";
	}

	@GetMapping("/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Vacante> listaVacantes = this.serviceVacantes.buscarTodas(page);
		model.addAttribute("vacantes", listaVacantes);
		return "vacantes/listVacantes";
	}

	@GetMapping("/create")
	public String crear(Vacante vacante, Model model) {
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
	public String guardar(Vacante vacante, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart) {

		if (result.hasErrors()) {

			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error:" + error.getDefaultMessage());
			}

			return "vacantes/formVacante";
		}

		if (!multiPart.isEmpty()) {
			// String ruta = "/empleos/img-vacantes/"; // Linux/MAC
			String ruta = "c:/empleos/img-vacantes/"; // Windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null) { // La imagen si se subio
				// Procesamos la variable nombreImagen
				vacante.setImagen(nombreImagen);
			}
		}

		this.serviceVacantes.guardar(vacante);
//		model.addAttribute("msg","Registro guardado");
		attributes.addFlashAttribute("msj", "Registro guardado");
		System.out.println("Vacante:" + vacante);
		return "redirect:/vacantes/index";
	}

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idVacante, RedirectAttributes attributes) {
		System.out.println("borrando vacante :" + idVacante);
		this.serviceVacantes.eliminar(idVacante);
		attributes.addFlashAttribute("msj", "La vacante fue eliminada!");
		return "redirect:/vacantes/index";
	}

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idVacante, Model model) {
		Vacante vacante = this.serviceVacantes.buscarPorId(idVacante);
		model.addAttribute("vacante", vacante);
		return "vacantes/formVacante";
	}

	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idVacante, Model model) {

		Vacante vacante = this.serviceVacantes.buscarPorId(idVacante);

		System.out.println("id vacante:" + idVacante);
		model.addAttribute("idVacante", idVacante);
		model.addAttribute("vacante", vacante);

		return "detalle";

	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));

	}

	/*
	 * la anotacion @ModelAttribute a nivel de metodo sirve para crear atributos que
	 * pueden ser usados implicitamente por todos los metodos del controlador
	 */
	@ModelAttribute
	public void setGenericos(Model model) {
		model.addAttribute("categorias", this.serviceCategorias.buscarTodas());
	}

}
