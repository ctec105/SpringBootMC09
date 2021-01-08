package com.aprendec;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.aprendec.model.Usuario;
import com.aprendec.repository.IUsuarioRepo;

@SpringBootTest
class SpringBootMc09ApplicationTests {

	@Autowired
	private IUsuarioRepo rep;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Test
	void crearUsuarioTest() {
		Usuario usuario = new Usuario();
		usuario.setId(2);
		usuario.setNombre("juan");
		usuario.setClave(encoder.encode("juan"));
		Usuario retorno = rep.save(usuario);
		assertTrue(retorno.getClave().equalsIgnoreCase(usuario.getClave()));
	}

}
