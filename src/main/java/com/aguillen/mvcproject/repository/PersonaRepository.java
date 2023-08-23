package com.aguillen.mvcproject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aguillen.mvcproject.entity.Persona;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Long> {

}
