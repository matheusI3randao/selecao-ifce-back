package com.selecao.ifce.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.selecao.ifce.model.Habilidade;

@Repository
public interface HabilidadeRepository extends CrudRepository<Habilidade, Long> {

}
