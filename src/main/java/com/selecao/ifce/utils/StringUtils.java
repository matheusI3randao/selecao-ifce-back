package com.selecao.ifce.utils;

import org.springframework.util.ObjectUtils;

public class StringUtils {

	private StringUtils() {
	}

	public static String tratarLike(String texto) {
		return ObjectUtils.isEmpty(texto) ? null : "%" + texto + "%";
	}

	public static String tratarString(String texto) {
		return ObjectUtils.isEmpty(texto) ? null : texto.toLowerCase();
	}
}
