package com.movie.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.model.dto.UserDTO;
import com.movie.model.vo.UserVO;
import com.movie.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService service;
			
	@ApiOperation(value = "Register users")
	@PostMapping		
	public UserDTO insert(@RequestBody UserVO userVO) {		
		return service.insert(userVO);		
	}
	
	@ApiOperation(value = "Update users.")
	@PutMapping		
	public UserDTO update(@RequestBody UserVO userVO) {		
		return service.update(userVO);		
	}
	
	@ApiOperation(value = "Find user.")
	@GetMapping("/{id}")		
	public UserDTO findById(@PathVariable Long id) {		
		return service.findDtoById(id);		
	}
	
}
