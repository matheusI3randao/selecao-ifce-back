package com.selecao.ifce.model;

public enum EnumEscolaridade {
	
	EDUCACAO_INFANTIL("Educação infantil"), 
	FUNDAMENTAL("Fundamental"), 
	MEDIO("Médio"),
	SUPERIOR("Superior (Graduação)"),
	POS_GRADUACAO("Pós-graduação"),
	MESTRADO("Mestrado"),
	DOUTORADO("Doutorado");

	private EnumEscolaridade(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public static String obterDescricao(final EnumEscolaridade enumMes) {
		for (final EnumEscolaridade element : values())
			if (element == enumMes) {
				return element.getDescricao();
			}

		return null;
	}
}
