package com.selecao.ifce.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class PessoaOutputDTO {

	private String cpf;
	private String nome;
	private Date dataNascimento;
	private String email;
	private String escolaridade;
	private String observacao;
	private List<HabilidadeDTO> habilidades;
}
