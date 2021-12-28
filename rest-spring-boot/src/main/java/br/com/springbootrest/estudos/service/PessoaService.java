package br.com.springbootrest.estudos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springbootrest.estudos.data.model.Pessoa;
import br.com.springbootrest.estudos.data.vo.PessoaVO;
import br.com.springbootrest.estudos.exception.ResourceNotFoundException;
import br.com.springbootrest.estudos.mapper.DozerMapper;
import br.com.springbootrest.estudos.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	public PessoaVO obterPorId(Long id) {

		return DozerMapper.parseObject(repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não encontrou nada com esse ID")), PessoaVO.class);
	}

	public List<PessoaVO> obterTodos() {
		return DozerMapper.parseListObjects(repository.findAll(), PessoaVO.class);
	}

	public PessoaVO criar(PessoaVO pessoaVO) {
		var entity = DozerMapper.parseObject(pessoaVO, Pessoa.class);
		return DozerMapper.parseObject(repository.save(entity), PessoaVO.class);
	}

	public PessoaVO atualizar(PessoaVO pessoaVO) {

		var entity = this.obterPorId(pessoaVO.getId());

		entity.setEndereco(pessoaVO.getEndereco());
		entity.setGenero(pessoaVO.getGenero());
		entity.setNome(pessoaVO.getNome());
		entity.setSobrenome(pessoaVO.getSobrenome());

		return this.criar(entity);
	}

	public void deletar(Long id) {
		repository.delete(DozerMapper.parseObject(this.obterPorId(id), Pessoa.class));
	}

}
