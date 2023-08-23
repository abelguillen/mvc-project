package com.aguillen.mvcproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
