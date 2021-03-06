package com.selecao.ifce;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				   .select()
				   .apis(RequestHandlerSelectors.any())
				   .paths(PathSelectors.any())
				   .build()
				   .apiInfo(metaData());
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder()
				   .title("CRUD de Pessoas REST API")
				   .description("API Rest criada para integração com o frontend do projeto de CRUD de Pessoas. \n\nBiblioteca de componentes utilizada: https://ng.ant.design/docs/introduce/en")
				   .version("1.0.0")
				   .build();
	}
}
