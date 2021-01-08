package com.aprendec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.aprendec.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bcryp;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	// Configuración del usuario y clave
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Para pbtener usuarios de memoria (es lo mismo que lo hecho en el aplication.properties, pero en código)
		/*auth.inMemoryAuthentication()
				.withUser("user")
				.password("user")
				.roles("USER")
			.and()
				.withUser("admin")
				.password("admin")
				.roles("USER", "ADMIN");*/
		
		// Para obtener usuarios de la base de datos
		auth.userDetailsService(userDetailsService).passwordEncoder(bcryp);
	}

	// Cualquier petición que entra tiene que estar autenticada, sino, no vamos a poder visualizar la respuesta
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Cualquier petición que entra tiene que estar autenticada
		http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
	}
	
}
