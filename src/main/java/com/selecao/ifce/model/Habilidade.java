package com.selecao.ifce.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity()
public class Habilidade implements Serializable {

	private static final long serialVersionUID = 5334282335082385635L;

	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty(message = "O campo descrição não pode ser vazio!")
	@Column(length = 50, nullable = false)
	private String descricao;
	
	public Habilidade(Long id) {
		this.id = id;
	}
}
