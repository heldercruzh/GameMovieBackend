package com.movie.exceptions;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.movie.exceptions.vo.ExceptionResponseVO;

import lombok.Generated;

@Generated
@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String HEADER_MESSAGE = "message";

	private static final String TITLE_ERRO_BUSINESS_RULE = "Business rule";
	private static final String TITLE_INVALID_PARAMETERS = "Invalid parameters";
	private static final String TITLE_DATA_ALREADY_REGISTERED = "Data already registered";
	private static final String TITLE_SERVER_ERROR = "Server error";
	private static final String TITLE_NOT_AUTORIZED = "Not authorized";
	private static final String TITLE_REGISTER_NOT_FOUND = "Register not found";

	private static final String TYPE_BUSINESS_RULES_VALIDATION = "Business rules validation";
	private static final String TYPE_PARAMETER_VALIDATION = "Parameter Validation";
	private static final String TYPE_DATA_ALREADY_REGISTERED = "Data already registered";
	private static final String TYPE_UNEXPECTED_ERROR = "Unexpected error";
	private static final String TYPE_NOT_AUTHORIZED = "Not authorized";
	private static final String TYPE_REGISTER_NOT_FOUND = "Register not found";

	@ExceptionHandler(MessageException.class)
	public ResponseEntity<Object> handleDadosJaCadastradosException(MessageException e, ServletWebRequest request) {
		logger.warn(e.getMessage());

		ExceptionResponseVO bodyExceptionResponse = criarExceptionResponse(TITLE_ERRO_BUSINESS_RULE,
				TYPE_BUSINESS_RULES_VALIDATION, Arrays.asList(e.getMessage()), request.getRequest().getRequestURI());

		HttpHeaders header = new HttpHeaders();
		header.add(HEADER_MESSAGE, e.getMessage());

		return handleExceptionInternal(e, bodyExceptionResponse, header, BAD_REQUEST, request);
	}
	
	@ExceptionHandler(InvalidParametersException.class)
	public ResponseEntity<Object> handleParametroInvalidoException(InvalidParametersException e, ServletWebRequest request) {
		logger.warn(e.getMessage());

		ExceptionResponseVO bodyExceptionResponse = criarExceptionResponse(TITLE_INVALID_PARAMETERS,
				TYPE_PARAMETER_VALIDATION, Arrays.asList(e.getMessage()), request.getRequest().getRequestURI());

		HttpHeaders header = new HttpHeaders();
		header.add(HEADER_MESSAGE, e.getMessage());

		return handleExceptionInternal(e, bodyExceptionResponse, header, BAD_REQUEST, request);
	}

	@ExceptionHandler(DataAlreadyRegisteredException.class)
	public ResponseEntity<Object> handleDadosJaCadastradosException(DataAlreadyRegisteredException e, ServletWebRequest request) {
		logger.warn(e.getMessage());

		ExceptionResponseVO bodyExceptionResponse = criarExceptionResponse(TITLE_DATA_ALREADY_REGISTERED,
				TYPE_DATA_ALREADY_REGISTERED, Arrays.asList(e.getMessage()), request.getRequest().getRequestURI());

		HttpHeaders header = new HttpHeaders();
		header.add(HEADER_MESSAGE, e.getMessage());

		return handleExceptionInternal(e, bodyExceptionResponse, header, BAD_REQUEST, request);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleNaoEncontradoException(NotFoundException e, ServletWebRequest request) {
		logger.warn(e.getMessage());

		ExceptionResponseVO bodyExceptionResponse = criarExceptionResponse(TITLE_REGISTER_NOT_FOUND,
				TYPE_REGISTER_NOT_FOUND, Arrays.asList(e.getMessage()), request.getRequest().getRequestURI());
		
		HttpHeaders header = new HttpHeaders();
		header.add(HEADER_MESSAGE, e.getMessage());

		return handleExceptionInternal(e, bodyExceptionResponse, header, NOT_FOUND, request);
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<Object> handleNaoAutorizadoException(UnauthorizedException e, ServletWebRequest request) {
		logger.warn(e.getMessage());

		ExceptionResponseVO bodyExceptionResponse = criarExceptionResponse(TITLE_NOT_AUTORIZED,
				TYPE_NOT_AUTHORIZED, Arrays.asList(e.getMessage()), request.getRequest().getRequestURI());
		
		HttpHeaders header = new HttpHeaders();
		header.add(HEADER_MESSAGE, e.getMessage());

		return handleExceptionInternal(e, bodyExceptionResponse, header, UNAUTHORIZED, request);
	}

	@ExceptionHandler(InvalidFiltersException.class)
	public ResponseEntity<Object> handleFiltrosInvalidosException(InvalidFiltersException e, ServletWebRequest request) {
		logger.warn(e.getMessage());
		
		ExceptionResponseVO bodyExceptionResponse = criarExceptionResponse(TITLE_INVALID_PARAMETERS,
				TYPE_PARAMETER_VALIDATION, Arrays.asList(e.getMessage()), request.getRequest().getRequestURI());

		HttpHeaders header = new HttpHeaders();
		header.add(HEADER_MESSAGE, e.getMessage());

		return handleExceptionInternal(e, bodyExceptionResponse, header, BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.warn(e.getMessage());

		ExceptionResponseVO bodyExceptionResponse = criarExceptionResponse(TITLE_INVALID_PARAMETERS,
				TYPE_PARAMETER_VALIDATION, e.getAllErrors().stream()
						.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()),
				request.getDescription(false).replace("uri=", ""));

		HttpHeaders header = new HttpHeaders();
		header.add(HEADER_MESSAGE, e.getObjectName());

		return handleExceptionInternal(e, bodyExceptionResponse, header, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUncaughtException(Exception e, ServletWebRequest request) {
		logger.error(e.getMessage(), e);

		ExceptionResponseVO bodyExceptionResponse = criarExceptionResponse(TITLE_SERVER_ERROR, TYPE_UNEXPECTED_ERROR,
				Arrays.asList(e.getMessage()), request.getRequest().getRequestURI());

		HttpHeaders header = new HttpHeaders();
		header.add(HEADER_MESSAGE, e.getMessage());

		return handleExceptionInternal(e, bodyExceptionResponse, header, INTERNAL_SERVER_ERROR, request);
	}

	private ExceptionResponseVO criarExceptionResponse(String title, String type, List<String> detail, String instance) {
		return ExceptionResponseVO.builder()
		.detail(detail)
		.instance(instance)
		.title(title)
		.type(type)
		.build();
	}

}