package com.aguillen.mvcproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aguillen.mvcproject.entity.Persona;
import com.aguillen.mvcproject.repository.PersonaRepository;

@Controller
@RequestMapping("/personas")
public class PersonaController {
	
	@Autowired
	private PersonaRepository repository;
	
	@GetMapping
	public String home(Model template) {
		return "personas";
	}
	
	@GetMapping("/lista")
	public String lista(Model template) {
		List<Persona> personas = (List<Persona>) repository.findAll();
		template.addAttribute("personas", personas);
		return "personas_lista";
	}
	
	@GetMapping("/nueva")
	public String nueva() {
		return "personas_nueva";
	}
	
	@PostMapping("/nueva")
	public String nuevaPost(
			@RequestParam(value = "nombre") String nombre,
			@RequestParam(value = "apellido") String apellido) {
		Persona persona = new Persona(nombre, apellido);
		try {
			repository.save(persona);
		} catch(Exception ex) {
			System.out.println("Ha ocurrido un error al intentar guardar una persona en la base de datos: " + ex.getMessage());
		}
		return "redirect:" + "/personas/lista";
	}

}
