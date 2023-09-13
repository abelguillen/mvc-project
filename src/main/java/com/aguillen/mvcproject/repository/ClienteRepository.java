package com.aguillen.mvcproject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aguillen.mvcproject.entity.Cliente;
import com.aguillen.mvcproject.entity.Persona;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	public Cliente findByNombre(String nombre);
	public Cliente findByApellido(String apellido);
}
