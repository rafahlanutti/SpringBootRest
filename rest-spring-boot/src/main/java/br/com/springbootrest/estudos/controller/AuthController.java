package br.com.springbootrest.estudos.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springbootrest.estudos.repository.UserRepository;
import br.com.springbootrest.estudos.security.AccountCredentialsVO;
import br.com.springbootrest.estudos.security.jwt.JwtTokenProvider;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider tokenProvider;

	@Autowired
	UserRepository repository;

	@PostMapping(value = "/signin", produces = { "application/json", "application/xml",
			"application/x-yaml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {

		var userName = data.getUsername();
		var password = data.getPassword();

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
			var user = repository.findByUsername(userName);
			if (user == null) {
				throw new UsernameNotFoundException("Username not found!");
			}

			var token = tokenProvider.createToken(userName, user.getRoles());

			Map<Object, Object> model = new HashMap<>();

			model.put("username", userName);
			model.put("token", token);

			return ok(model);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException(" Invalid username/password");
		}

	}

}
