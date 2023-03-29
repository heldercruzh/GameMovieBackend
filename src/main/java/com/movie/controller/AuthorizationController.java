package com.movie.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.model.dto.UserDTO;
import com.movie.model.vo.LoginVO;
import com.movie.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/authorization")
@RequiredArgsConstructor
public class AuthorizationController {

	private final UserService service;
		
	@ApiOperation(value = "Obtain authorization.")
	@PostMapping()		
	public UserDTO login(@RequestBody LoginVO loginVO) {		
		return service.login(loginVO);		
	}
	
}
