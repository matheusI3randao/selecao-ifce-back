package com.selecao.ifce.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class PessoaInputDTO {
	private Long id;
	private String cpf;
	private String nome;
	private Date dataNascimento;
	private String email;
	private String escolaridade;
	private String observacao;
	private List<HabilidadeDTO> habilidades;
}
