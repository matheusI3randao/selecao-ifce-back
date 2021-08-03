package com.selecao.ifce.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.selecao.ifce.dto.PessoaInputDTO;
import com.selecao.ifce.exception.PessoaException;
import com.selecao.ifce.model.Habilidade;
import com.selecao.ifce.model.Pessoa;
import com.selecao.ifce.repository.HabilidadeRepository;
import com.selecao.ifce.repository.PessoaRepository;
import com.selecao.ifce.utils.StringUtils;

@Service
public class PessoaService {

	private ModelMapper modelMapper = new ModelMapper();
	private PessoaRepository repository;
	private HabilidadeRepository habilidadeRepository;

	@Autowired
	public PessoaService(PessoaRepository repository, HabilidadeRepository habilidadeRepository) {
		this.repository = repository;
		this.habilidadeRepository = habilidadeRepository;
	}

	public Page<Pessoa> findAll(String nome, String cpf, Integer pageNo, Integer pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		cpf = StringUtils.tratarLike(cpf);
		nome = StringUtils.tratarLike(StringUtils.tratarString(nome));

		return this.repository.findAll(nome, cpf, paging);
	}

	public Optional<Pessoa> findById(Long id) {
		return this.repository.findById(id);
	}

	public Pessoa save(PessoaInputDTO pessoaInput) {
		if (verifyDuplicateCpf(pessoaInput.getCpf()))
			throw new PessoaException("O CPF " + pessoaInput.getCpf() + " já está cadastrado!");

		Pessoa pessoa = modelMapper.map(pessoaInput, Pessoa.class);

		// Setando ID da pessoa pra null pois o ModelMapper converte pelo nome
		// independente da classe onde esteja mapeado. Nesse caso, ele está setando o ID
		// da habilidade na pessoa
		pessoa.setId(null);

		return this.repository.save(pessoa);
	}

	public Pessoa update(PessoaInputDTO pessoaInput) {
		Pessoa pessoa = modelMapper.map(pessoaInput, Pessoa.class);

		return this.repository.save(pessoa);
	}

	public void delete(Long id) {
		this.repository.delete(new Pessoa(id));
	}

	private boolean verifyDuplicateCpf(String cpf) {
		return this.repository.findByCpf(cpf) != null;
	}

	// Código feito somente para dar carga na tabela de habilidades
	// (favor desconsiderar)
	public void cargaHabilidade() {
		List<String> habilidades = Arrays.asList("JAVA", "ANGULAR", "REACT", "NODE", "SPRING BOOT", "HIBERNATE",
				"POSTGRES");
		habilidades.forEach(i -> {
			Habilidade hab = Habilidade.builder().descricao(i).build();
			habilidadeRepository.save(hab);
		});
	}
}
