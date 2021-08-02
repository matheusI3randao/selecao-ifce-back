package com.selecao.ifce.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity()
public class Pessoa implements Serializable {

	private static final long serialVersionUID = -5569618684988078706L;

	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "O campo CPF é obrigatório!")
	@Column(length = 11, nullable = false)
	private String cpf;

	@NotBlank(message = "O campo Nome é obrigatório!")
	@Column(length = 60, nullable = false)
	private String nome;

	@Column(length = 70, nullable = true)
	private String email;

	@Column(length = 500, nullable = true)
	private String observacao;

	@Enumerated(EnumType.STRING)
	private EnumEscolaridade escolaridade;

	@NotBlank(message = "O campo Data de Nascimento é obrigatório!")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNascimento;

	@JoinTable(name = "HABILIDADE_PESSOA", joinColumns = { @JoinColumn(name = "PESSOA_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "HABILIDADE_ID") })
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Habilidade> habilidades;

	@CreationTimestamp()
	private LocalDateTime dataCadastro;

	public Pessoa() {
	}

	public Pessoa(Long id) {
		this.id = id;
	}
}
