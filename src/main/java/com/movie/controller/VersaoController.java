package com.movie.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Generated;

@Generated
@RestController
@RequestMapping("/versao")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VersaoController {

	@Value("${versao}")
	private String versao;
	
	@Value("${server.servlet.context-path}")
	private String servico;
	
	@Value("${ambiente}")
	private String ambiente;

	@GetMapping()
	public Map<String, String>  getVersao() {
		return Map.of("versao", versao, "ambiente", ambiente, "servico", servico);
	}
	
}
