package com.aprendec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aprendec.model.Usuario;

public interface IUsuarioRepo extends JpaRepository<Usuario, Integer> {
	
	Usuario findByNombre(String nomre);

}
