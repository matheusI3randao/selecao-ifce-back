package com.selecao.ifce.controller;

import java.util.Optional;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.selecao.ifce.dto.PessoaInputDTO;
import com.selecao.ifce.dto.PessoaListagemDTO;
import com.selecao.ifce.dto.PessoaOutputDTO;
import com.selecao.ifce.exception.PessoaException;
import com.selecao.ifce.model.Pessoa;
import com.selecao.ifce.service.PessoaService;
import com.selecao.ifce.utils.ModelMapperUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = "Pessoas", value = "Endpoints de CRUD de Pessoas")
@RequestMapping(path = "pessoas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaController {

	private ModelMapper modelMapper = new ModelMapper();
	private PessoaService service;

	@Autowired
	public PessoaController(PessoaService service) {
		this.service = service;
	}

	@GetMapping()
	@ApiOperation(value = "Busca todas as Pessoas e pode ser filtrado por CPF e Nome")
	public ResponseEntity<Page<PessoaListagemDTO>> findAll(@QueryParam("nome") String nome,
			@QueryParam("cpf") String cpf,
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer pageNo,
			@RequestParam(value = "size", required = false, defaultValue = "10") Integer pageSize) {

		Page<Pessoa> page = service.findAll(nome, cpf, pageNo, pageSize);
		Page<PessoaListagemDTO> pageDTO = ModelMapperUtils.mapEntityPageIntoDtoPage(page, PessoaListagemDTO.class);

		return new ResponseEntity<>(pageDTO, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Busca um cadastro de Pessoa pelo ID")
	public ResponseEntity<PessoaOutputDTO> findById(@PathVariable("id") Long id) {
		Optional<Pessoa> empresa = service.findById(id);
		if (!empresa.isPresent())
			return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(modelMapper.map(empresa.get(), PessoaOutputDTO.class), new HttpHeaders(),
				HttpStatus.OK);
	}

	@PostMapping()
	@ApiOperation(value = "Cadastra uma Pessoa")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso ao cadastrar pessoa"),
			@ApiResponse(code = 500, message = "Erro ao cadastrar pessoa!") })
	public ResponseEntity<PessoaOutputDTO> findById(@Valid @RequestBody PessoaInputDTO pessoaInput) {
		try {
			Pessoa pessoa = service.save(pessoaInput);

			return new ResponseEntity<>(modelMapper.map(pessoa, PessoaOutputDTO.class), new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			throw new PessoaException(e.getMessage());
		}
	}

	@PutMapping()
	@ApiOperation(value = "Atualizar o cadastro de uma Pessoa")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso ao atualizar pessoa"),
			@ApiResponse(code = 500, message = "Erro ao atualizar pessoa!") })
	public ResponseEntity<PessoaOutputDTO> update(@Valid @RequestBody PessoaInputDTO pessoaInput) {
		try {
			Pessoa pessoa = service.update(pessoaInput);

			return new ResponseEntity<>(modelMapper.map(pessoa, PessoaOutputDTO.class), new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			throw new PessoaException("Erro ao cadastrar a pessoa! Entre em contato com o adminsitrador do sistema.");
		}
	}

	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "Deleta o cadastro de uma Pessoa")
	public void delete(@PathVariable Long id) {
		try {
			service.delete(id);
		} catch (Exception e) {
			throw new PessoaException("Erro ao excluir a pessoa! Entre em contato com o adminsitrador do sistema.");
		}
	}
}
