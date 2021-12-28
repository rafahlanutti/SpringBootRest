package br.com.springbootrest.estudos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springbootrest.estudos.core.data.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

}
