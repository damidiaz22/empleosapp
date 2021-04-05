package com.damidiaz.empleos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.damidiaz.empleos.model.Usuario;
import com.damidiaz.empleos.service.UsuariosService;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	
	@Autowired
	private UsuariosService serviceUsuarios;
    
    @GetMapping("/index")
	public String mostrarIndex(Model model) {

    	List<Usuario> listaUsuarios = this.serviceUsuarios.buscarTodos();
		model.addAttribute("usuarios", listaUsuarios);
    	
    	return "usuarios/listUsuarios";
	}
    
    @GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {		    	
		
    	this.serviceUsuarios.eliminar(idUsuario);
    	attributes.addFlashAttribute("msj","el usuario fue eliminado!");
		return "redirect:/usuarios/index";
	}
}
