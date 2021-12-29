package br.com.springbootrest.estudos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.springbootrest.estudos.core.data.model.Pessoa;
import br.com.springbootrest.estudos.core.data.vo.PessoaVO;
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

	public Page<PessoaVO> obterPorPaginacao(PageRequest pageble) {
		var pagina = repository.findAll(pageble);
		return pagina.map(this::convertToPessoaVO);

	}

	public Page<PessoaVO> obterPeloNome(String nome, PageRequest pageble) {
		var pagina = repository.obterPeloNome(nome, pageble);
		return pagina.map(this::convertToPessoaVO);

	}

	private PessoaVO convertToPessoaVO(Pessoa entity) {
		return DozerMapper.parseObject(entity, PessoaVO.class);
	}

	public PessoaVO criar(PessoaVO pessoaVO) {
		var entity = DozerMapper.parseObject(pessoaVO, Pessoa.class);
		return DozerMapper.parseObject(repository.save(entity), PessoaVO.class);
	}

	public PessoaVO atualizar(PessoaVO pessoaVO) {

		var entity = this.obterPorId(pessoaVO.getKey());

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
