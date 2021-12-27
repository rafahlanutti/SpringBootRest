package br.com.springbootrest.estudos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springbootrest.estudos.model.Pessoa;
import br.com.springbootrest.estudos.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Pessoa> findAll() {
		return service.obterTodos();
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Pessoa findById(@PathVariable("id") String id) {
		return service.obterPorId(id);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Pessoa create(@RequestBody Pessoa pessoa) {
		return service.criar(pessoa);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Pessoa put(@RequestBody Pessoa pessoa) {
		return service.atualizar(pessoa);
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable("id") String id) {
		service.deletar(id);
	}

}
