package com.aguillen.mvcproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PostMapping("/actualizar")
	public String actualizarPut(
			@RequestParam(value = "id") Long id,
			@RequestParam(value = "nombre") String nombre,
			@RequestParam(value = "apellido") String apellido) {
		try {
			repository.save(new Persona(id, nombre, apellido));
		} catch(Exception ex) {
			System.out.println("Ha ocurrido un error al intentar actualizar una persona en la base de datos: " + ex.getMessage());
		}
		return "redirect:" + "/personas/lista";
	}
	
	@GetMapping("/actualizar/{id}")
	public String actualizar(@PathVariable(value = "id") Long id, Model template) {
		Persona persona = repository.findById(id).get();
		template.addAttribute("persona", persona);
		return "personas_actualizar";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		try {
			repository.deleteById(id);
		} catch(Exception ex) {
			System.out.println("Ha ocurrido un error al intentar eliminar una persona en la base de datos: " + ex.getMessage());
		}
		return "redirect:" + "/personas/lista";
	}
	
	@GetMapping("/buscar/nombre")
	public String buscarNombre() {
		return "personas_buscarNombre";
	}
	
	@PostMapping("/buscar/nombre")
	public String buscarNombre(
			@RequestParam(value = "nombre") String nombre,
			Model template) {
		Persona persona = repository.findByNombre(nombre);
		template.addAttribute("id", persona.getId());
		template.addAttribute("nombre", persona.getNombre());
		template.addAttribute("apellido", persona.getApellido());
		return "personas_buscarNombre";
	}
	
	@GetMapping("/buscar/apellido")
	public String buscarApellido() {
		return "personas_buscarApellido";
	}
	
	@PostMapping("/buscar/apellido")
	public String buscarApellido(
			@RequestParam(value = "apellido") String apellido,
			Model template) {
		Persona persona = repository.findByApellido(apellido);
		template.addAttribute("id", persona.getId());
		template.addAttribute("nombre", persona.getNombre());
		template.addAttribute("apellido", persona.getApellido());
		return "personas_buscarApellido";
	}
	

}
