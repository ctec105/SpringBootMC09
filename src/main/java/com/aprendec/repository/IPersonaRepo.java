package com.aprendec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aprendec.model.Persona;

public interface IPersonaRepo extends JpaRepository<Persona, Integer> {

}
