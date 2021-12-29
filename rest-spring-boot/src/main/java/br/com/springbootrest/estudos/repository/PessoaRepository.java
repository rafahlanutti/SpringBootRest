package br.com.springbootrest.estudos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.springbootrest.estudos.core.data.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	@Query("SELECT p FROM Pessoa p WHERE p.nome LIKE LOWER(CONCAT('%', :nome, '%'))")
	Page<Pessoa> obterPeloNome(@Param("nome") String nome, Pageable pageable);
}
