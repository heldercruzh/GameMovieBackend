package com.movie.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VersaoControllerTest extends AbstractController {

	private static final String RESOURCE = "/movies/versao";
	private static StringBuilder path = new StringBuilder(URL);
	
	@Test
	@Order(1)
	void versao() {

		ParameterizedTypeReference<Map<String, String>> responseType = new ParameterizedTypeReference<>() {};
		
		ResponseEntity<Map<String, String>> response = this.restTemplate.exchange(path.toString(), HttpMethod.GET, null, responseType);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@BeforeEach
	void inicializar() {
		path.append(RESOURCE);
	}

	@AfterEach
	void finalizar() {
		path = new StringBuilder(URL);
	}
}
