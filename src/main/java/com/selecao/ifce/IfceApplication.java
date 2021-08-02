package com.selecao.ifce;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.selecao.ifce.exception.ApiExceptionHandler;
import com.selecao.ifce.service.PessoaService;

@SpringBootApplication
@Import(ApiExceptionHandler.class)
public class IfceApplication {

	@Autowired
	private PessoaService pessoaService;

	public static void main(String[] args) {
		SpringApplication.run(IfceApplication.class, args);
	}

	@PostConstruct()
	private void init() {
		this.pessoaService.cargaHabilidade();
	}
}
