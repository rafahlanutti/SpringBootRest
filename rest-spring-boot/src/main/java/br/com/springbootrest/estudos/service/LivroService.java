package br.com.springbootrest.estudos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springbootrest.estudos.core.data.model.Livro;
import br.com.springbootrest.estudos.core.data.vo.LivroVO;
import br.com.springbootrest.estudos.exception.ResourceNotFoundException;
import br.com.springbootrest.estudos.mapper.DozerMapper;
import br.com.springbootrest.estudos.repository.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository repository;

	public LivroVO obterPorId(Long id) {

		return DozerMapper.parseObject(repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não encontrou nada com esse ID")), LivroVO.class);
	}

	public List<LivroVO> obterTodos() {
		return DozerMapper.parseListObjects(repository.findAll(), LivroVO.class);
	}

	public LivroVO criar(LivroVO livroVO) {
		var entity = DozerMapper.parseObject(livroVO, Livro.class);
		return DozerMapper.parseObject(repository.save(entity), LivroVO.class);
	}

	public LivroVO atualizar(LivroVO livroVO) {

		var entity = this.obterPorId(livroVO.getKey());
		entity.setAutor(livroVO.getAutor());
		entity.setDataDeLancamento(livroVO.getDataDeLancamento());
		entity.setPreco(livroVO.getPreco());
		entity.setTitulo(livroVO.getTitulo());

		return this.criar(entity);
	}

	public void deletar(Long id) {
		repository.delete(DozerMapper.parseObject(this.obterPorId(id), Livro.class));
	}

}
