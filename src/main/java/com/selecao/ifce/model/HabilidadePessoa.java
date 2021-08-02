//package com.selecao.ifce.model;
//
//import java.io.Serializable;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//
//import lombok.Data;
//
//@Data
//@Entity()
//public class HabilidadePessoa implements Serializable {
//
//	private static final long serialVersionUID = 1627415680800542367L;
//
//	@Id()
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//
//	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private Pessoa pessoa;
//
//	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private Habilidade habilidade;
//}
