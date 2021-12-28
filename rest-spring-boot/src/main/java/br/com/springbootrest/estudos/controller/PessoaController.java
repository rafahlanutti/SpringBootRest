package br.com.springbootrest.estudos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springbootrest.estudos.data.vo.PessoaVO;
import br.com.springbootrest.estudos.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService service;

	@GetMapping
	public List<PessoaVO> findAll() {
		return service.obterTodos();
	}

	@GetMapping("/{id}")
	public PessoaVO findById(@PathVariable("id") Long id) {
		return service.obterPorId(id);
	}

	@PostMapping
	public PessoaVO create(@RequestBody PessoaVO pessoa) {
		return service.criar(pessoa);
	}

	@PutMapping
	public PessoaVO put(@RequestBody PessoaVO pessoa) {
		return service.atualizar(pessoa);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.deletar(id);
		return ResponseEntity.ok().build();
	}

}
