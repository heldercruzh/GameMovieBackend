package com.movie.service;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import com.movie.constants.ErrorMessage;
import com.movie.exceptions.NotFoundException;
import com.movie.model.entity.MovieEntity;
import com.movie.repository.MovieRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {
	
	private final MovieRepository movieRepository;

	public MovieEntity findById(Long id) {		
		return movieRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.NON_EXISTENT_MOVIE));
	}
		
	public void addMoviesIfThereAreNot() {
		
		List<MovieEntity> movies = movieRepository.findAll();
		
		if(!movies.isEmpty()) {
			return;
		}
		
		// https://exame.com/pop/os-10-melhores-filmes-e-series-segundo-imdb-e-onde-assistir/
		movies = List.of(
				new MovieEntity(1L, "The Shawshank Redemption (1994)", BigDecimal.valueOf(9.2), null),
				new MovieEntity(2L, "The Godfather (1972)", BigDecimal.valueOf(9.1), null),
				new MovieEntity(3L, "The Godfather: Part II (1974)", BigDecimal.valueOf(9.0), null),
				new MovieEntity(4L, "Batman: the Dark Knight (2008)", BigDecimal.valueOf(9.0), null),
				new MovieEntity(5L, "12 Angry Men (1957)", BigDecimal.valueOf(8.9), null),
				new MovieEntity(6L, "Schindler's List (1993)", BigDecimal.valueOf(8.9), null),
				new MovieEntity(7L, "The Lord of the Rings: The Return of the King (2003)", BigDecimal.valueOf(8.9), null),
				new MovieEntity(8L, "Pulp Fiction (1994)", BigDecimal.valueOf(8.8), null),
				new MovieEntity(9L, "Planet Earth II (2016)", BigDecimal.valueOf(9.5), null),
				new MovieEntity(10L, "Planet Earth (2006)", BigDecimal.valueOf(9.4), null),
				new MovieEntity(10L, "Band of Brothers (2001)", BigDecimal.valueOf(9.4), null),
				new MovieEntity(10L, "Chernobyl (2019)", BigDecimal.valueOf(9.3), null),
				new MovieEntity(10L, "The Wire (2002)", BigDecimal.valueOf(9.3), null),
				new MovieEntity(10L, "Blue Planet II (2017)", BigDecimal.valueOf(9.3), null),
				new MovieEntity(10L, "Our Planet (2019)", BigDecimal.valueOf(9.2), null),
				new MovieEntity(10L, "Cosmos: A Spacetime Odyssey (2014)", BigDecimal.valueOf(9.2), null)				
		);
		
		movieRepository.saveAll(movies);
		
	}
	
	
}
