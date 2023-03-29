package com.movie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movie.constants.ErrorMessage;
import com.movie.exceptions.MessageException;
import com.movie.exceptions.NotFoundException;
import com.movie.model.dto.AnswerDTO;
import com.movie.model.entity.GameEntity;
import com.movie.model.entity.MovieEntity;
import com.movie.model.entity.QuestionEntity;
import com.movie.model.vo.AnswerVO;
import com.movie.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {
	
	private final QuestionRepository questionRepository;
	private final MovieService movieService;

	public QuestionEntity provideQuestion(GameEntity gameEntity) {
		
		Optional<QuestionEntity> optionalQuestionEntity = this.questionRepository.findByGameAndAnswerMovie(gameEntity, null);
		
		if(optionalQuestionEntity.isPresent()) {
			return optionalQuestionEntity.get();
		}
		
		
		List<MovieEntity> listFirstMovieAlreadyQuestion = 
				this.questionRepository.findAllFirstMovieAlreadyQuestion(gameEntity.getUser().getId());
		
		List<MovieEntity> listSecondMovieAlreadyQuestion = 
				this.questionRepository.findAllFirstMovieAlreadyQuestion(gameEntity.getUser().getId());
		
		Optional<GameEntity> optionalGameEntity;
		
		MovieEntity secondMovie = new MovieEntity();
		
		for(int i = 0; i <= listFirstMovieAlreadyQuestion.size() - 1; i++) {
				
			secondMovie = new MovieEntity();
			
			for(int j = 0; j <= listSecondMovieAlreadyQuestion.size() - 1; j++) {
				
				if(listFirstMovieAlreadyQuestion.get(i).getId().equals(listSecondMovieAlreadyQuestion.get(j).getId())) {
					continue;					
				}
				
				if(listFirstMovieAlreadyQuestion.get(i).getScore().compareTo(listSecondMovieAlreadyQuestion.get(j).getScore()) == 0) {
					continue;					
				}
				
				secondMovie = listSecondMovieAlreadyQuestion.get(j);
				break;
			}
			
			
			optionalGameEntity =
			this.questionRepository.findGameCombinationMoviesInQuestion(
					gameEntity.getUser().getId(),
					listFirstMovieAlreadyQuestion.get(i).getId(), 
					secondMovie.getId()
			);
			
			if(!optionalGameEntity.isPresent()) {
				
				QuestionEntity questionEntity = QuestionEntity.builder()
												  .id(null)
												  .game(gameEntity)
												  .firstMovie(listFirstMovieAlreadyQuestion.get(i))
												  .secondMovie(secondMovie)
												  .build();
				
				return this.save(questionEntity);
				
			}
			
		}
		
		return null;
		
	}
		
	@Transactional
	public AnswerDTO validateAnswer(AnswerVO answerVO) {
		
		QuestionEntity questionEntity = this.findById(answerVO.getId());
		
		if(!(answerVO.getIdChosenMovie().equals(answerVO.getIdFirstMovie())) && !(answerVO.getIdChosenMovie().equals(answerVO.getIdSecondMovie()))) {
			throw new MessageException(ErrorMessage.CHOSE_MOVIE_DIREFENTE);
		}
		
		if(questionEntity.getAnswerMovie() != null) {
			throw new MessageException(ErrorMessage.QUESTION_ALREADY_ANSWERED);
		}	
		
		MovieEntity firstMovie = movieService.findById(answerVO.getIdFirstMovie());
		MovieEntity secondMovie = movieService.findById(answerVO.getIdSecondMovie());

		Long idTheBestScoreMovie;
		
		if(firstMovie.getScore().compareTo(secondMovie.getScore()) == -1) {
			idTheBestScoreMovie = secondMovie.getId();
		} else {
			idTheBestScoreMovie = firstMovie.getId();
		}
		
		boolean blAnswer; 
		
		if(idTheBestScoreMovie.equals(answerVO.getIdChosenMovie())) {
			blAnswer = true;
		} else {
			blAnswer = false;
		}
		
		questionEntity.setAnswerMovie(movieService.findById(idTheBestScoreMovie));
		this.save(questionEntity);
		
		return AnswerDTO.builder()
						.id(questionEntity.getId())
						.idGame(questionEntity.getGame().getId())
						.blAnswer(blAnswer)
						.scoreGame(null)
						.scoreUser(null)
					    .build();
	}
	
	public QuestionEntity findById(Long id) {
		return questionRepository.findById(id)
				   .orElseThrow(() -> new NotFoundException(ErrorMessage.NON_EXISTENT_QUESTION));
	}
	
	public QuestionEntity save(QuestionEntity questionEntity) {
		return questionRepository.save(questionEntity);
	}
	
}
