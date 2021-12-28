package br.com.springbootrest.estudos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springbootrest.estudos.exception.ResourceNotFoundException;
import br.com.springbootrest.estudos.model.Pessoa;
import br.com.springbootrest.estudos.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	public Pessoa obterPorId(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não encontrou nada com esse ID"));
	}

	public List<Pessoa> obterTodos() {
		return repository.findAll();
	}

	public Pessoa criar(Pessoa pessoa) {
		return repository.save(pessoa);
	}

	public Pessoa atualizar(Pessoa pessoa) {

		var entity = this.obterPorId(pessoa.getId());

		entity.setEndereco(pessoa.getEndereco());
		entity.setGenero(pessoa.getGenero());
		entity.setNome(pessoa.getNome());
		entity.setSobrenome(pessoa.getSobrenome());

		return repository.save(entity);
	}

	public void deletar(Long id) {

		repository.delete(this.obterPorId(id));
	}

}
