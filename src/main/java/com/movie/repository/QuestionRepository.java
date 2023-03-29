package com.movie.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.movie.model.entity.GameEntity;
import com.movie.model.entity.MovieEntity;
import com.movie.model.entity.QuestionEntity;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
	
	@Query("select m from MovieEntity m "
		     + "where m.id not in ("			
				 +"select fm.id from QuestionEntity q "
				 + "join q.firstMovie fm "
				 + "join q.secondMovie sm "
				 + "join q.game g "
				 + "join g.user u "
				 + "where u.id = :idUser "
			 +")")
	List<MovieEntity> findAllFirstMovieAlreadyQuestion(Long idUser); 
	
	@Query("select m from MovieEntity m "
		     + "where m.id not in ( "			
				 +"select sm.id from QuestionEntity q "
				 + "join q.firstMovie fm "
				 + "join q.secondMovie sm "
				 + "join q.game g "
				 + "join g.user u "
				 + "where u.id = :idUser "
			 +")")
	List<MovieEntity> findAllSecondMovieAlreadyQuestion(Long idUser); 
		
	@Query("select g from QuestionEntity q "			
			 + "join q.game g "
			 + "join g.user u "
			 + "where "
			 + "u.id = :idUser and g.id in ("
			 	 + "select g.id from QuestionEntity q "
			 	 + "join q.firstMovie fm "
				 + "join q.secondMovie sm "
				 + "join q.game g "
				 + "join g.user u "
				 + "where "			 	
			 	 + "(fm.id = :idFirstMovie and sm.id = :idSecondMovie) and "
	 		 	 + "(fm.id = :idSecondMovie and sm.id = :idFirstMovie) and "
	 		 	 + "(fm.id <> sm.id)"
	 		+ ")")
	Optional<GameEntity> findGameCombinationMoviesInQuestion(Long idUser, Long idFirstMovie, Long idSecondMovie); 
	
	Optional<QuestionEntity> findByGameAndAnswerMovie(GameEntity game, MovieEntity answerMovie);
	
}
