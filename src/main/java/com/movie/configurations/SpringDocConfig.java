package com.movie.configurations;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.Generated;

@Generated
@Configuration
public class SpringDocConfig {

	//http://localhost:8080/movies/swagger-ui/index.html
	@Bean
	public GroupedOpenApi swagger() {
		return GroupedOpenApi.builder()
				.group("com.movie")
				.packagesToScan("com.movie.controller")
				.build();
	}
}
