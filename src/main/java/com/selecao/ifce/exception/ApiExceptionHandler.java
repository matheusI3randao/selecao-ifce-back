package com.selecao.ifce.exception;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(PessoaException.class)
	public ResponseEntity<Object> handleNegocio(PessoaException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;

		ResponseMessage problema = new ResponseMessage();
		problema.setStatus(status.value());
		problema.setMessage(ex.getMessage());
		problema.setTimestamp(LocalDateTime.now());

		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ResponseMessage problema = new ResponseMessage();
		problema.setStatus(status.value());
		problema.setTimestamp(LocalDateTime.now());

		StringBuilder sb = new StringBuilder();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			sb.append((sb.length() > 0 ? "," : "") + nome + ":" + mensagem);
		}
		problema.setMessage(sb.toString());

		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}
}
