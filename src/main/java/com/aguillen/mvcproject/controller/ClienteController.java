package com.aguillen.mvcproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

}
