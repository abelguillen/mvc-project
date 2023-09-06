package com.aguillen.mvcproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aguillen.mvcproject.entity.Cliente;
import com.aguillen.mvcproject.entity.Persona;
import com.aguillen.mvcproject.repository.ClienteRepository;
import com.aguillen.mvcproject.repository.PersonaRepository;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository repository;
	
	@GetMapping
	public String home(Model template) {
		return "clientes";
	}
	
	@GetMapping("/lista")
	public String lista(Model template) {
		List<Cliente> clientes = (List<Cliente>) repository.findAll();
		template.addAttribute("clientes", clientes);
		return "clientes_lista";
	}
	
	@GetMapping("/nuevo")
	public String nuevo() {
		return "clientes_nuevo";
	}
	
	@PostMapping("/nuevo")
	public String nuevaPost(
			@RequestParam(value = "nombre") String nombre,
			@RequestParam(value = "apellido") String apellido,
			@RequestParam(value = "direccion") String direccion){
		Cliente cliente = new Cliente(nombre, apellido, direccion);
		try {
			repository.save(cliente);
		} catch(Exception ex) {
			System.out.println("Ha ocurrido un error al intentar guardar al cliente en la base de datos: " + ex.getMessage());
		}
		return "redirect:" + "/clientes/lista";
	}
	
	@PostMapping("/actualizar")
	public String actualizarPut(
			@RequestParam(value = "id") Long id,
			@RequestParam(value = "nombre") String nombre,
			@RequestParam(value = "apellido") String apellido,
			@RequestParam(value = "direccion") String direccion) {
		try {
			repository.save(new Cliente(id, nombre, apellido, direccion));
		} catch(Exception ex) {
			System.out.println("Ha ocurrido un error al intentar actualizar un cliente en la base de datos: " + ex.getMessage());
		}
		return "redirect:" + "/clientes/lista";
	}
	
	@GetMapping("/actualizar/{id}")
	public String actualizar(@PathVariable(value = "id") Long id, Model template) {
		Cliente cliente = repository.findById(id).get();
		template.addAttribute("cliente", cliente);
		return "clientes_actualizar";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		try {
			repository.deleteById(id);
		} catch(Exception ex) {
			System.out.println("Ha ocurrido un error al intentar eliminar un cliente en la base de datos: " + ex.getMessage());
		}
		return "redirect:" + "/clientes/lista";
	}
}
