package com.selecao.ifce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.selecao.ifce.model.Pessoa;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

	@Query(value = ""
		    + "SELECT distinct p "
		    + "  FROM Pessoa p"
		    + "  left join p.habilidades "
			+ "WHERE "
			+ "  (:nome is null or lower(p.nome) like :nome) AND "
			+ "  (:cpf is null or p.cpf like :cpf ) ")
	public Page<Pessoa> findAll(@Param("nome") String nome, @Param("cpf") String cpf, Pageable pageable);
}
