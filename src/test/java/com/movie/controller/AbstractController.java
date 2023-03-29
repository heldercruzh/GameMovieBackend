package com.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

public abstract class AbstractController implements BaseControllerIT {

	@Autowired
	protected TestRestTemplate restTemplate;

}