package com.movie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movie.constants.ErrorMessage;
import com.movie.exceptions.MessageException;
import com.movie.exceptions.NotFoundException;
import com.movie.model.dto.AnswerDTO;
import com.movie.model.dto.GameDTO;
import com.movie.model.dto.QuestionDTO;
import com.movie.model.dto.RankingDTO;
import com.movie.model.entity.GameEntity;
import com.movie.model.entity.QuestionEntity;
import com.movie.model.entity.UserEntity;
import com.movie.model.mapper.GameMapper;
import com.movie.model.mapper.QuestionMapper;
import com.movie.model.validators.AnswerVoValidator;
import com.movie.model.vo.AnswerVO;
import com.movie.repository.GameRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameService {
	
	private final UserService userService;
	private final MovieService movieService;
	private final QuestionService questionService;
	private final GameMapper gameMapper;
	private final QuestionMapper questionMapper;
	private final GameRepository gameRepository;
	private final AnswerVoValidator answerVoValidator;
	
	@Transactional
	public GameDTO start(Long id) {
		
		this.movieService.addMoviesIfThereAreNot();
		
		UserEntity userEntity = this.userService.findById(id);
		
		Optional<GameEntity> optionalGameEntity = this.gameRepository.findByUserAndBlOpen(userEntity, true);
		
		if(optionalGameEntity.isPresent()) {
			return this.gameMapper.toDTO(optionalGameEntity.get());
		}
				
		GameEntity gameEntity = new GameEntity();
		gameEntity.setUser(this.userService.findById(id));		
		gameEntity.setBlOpen(true);
		
		GameEntity gameEntitySave = this.gameRepository.save(gameEntity);
		
		return this.gameMapper.toDTO(gameEntitySave);		
	}

	public GameDTO stop(Long id) {
		
		GameEntity gameEntity = this.findById(id);
		gameEntity.setBlOpen(false);		
		
		GameEntity gameEntitySave = this.gameRepository.save(gameEntity);
		
		return this.gameMapper.toDTO(gameEntitySave);
	}

	public GameEntity findById(Long id) {
		return this.gameRepository.findById(id)
				   .orElseThrow(() -> new NotFoundException(ErrorMessage.NON_EXISTENT_GAME));
	}
	
	public QuestionDTO provideQuestion(Long idGame) {
		
		GameEntity gameEntity = this.findById(idGame);
		
		if(!gameEntity.isBlOpen()) {
			throw new MessageException(ErrorMessage.UNABLE_PROVIDE_QUESTION_FOR_GAME_CLODED);
		}
		
		QuestionEntity questionEntity = this.questionService.provideQuestion(gameEntity);
		
		if(questionEntity == null) {
			throw new MessageException(ErrorMessage.NO_MORE_QUESTIONS_AVAILABLE);
		}
		
		return questionMapper.toDTO(questionEntity);		
	}

	@Transactional
	public AnswerDTO validateAnswer(AnswerVO answerVO) {	
		
		this.answerVoValidator.run(answerVO);
		
		AnswerDTO answerDTO = this.questionService.validateAnswer(answerVO);
		
		GameEntity gameEntity = this.findById(answerDTO.getIdGame());
		
		if(!gameEntity.isBlOpen()) {
			throw new MessageException(ErrorMessage.UNABLE_PROVIDE_ANSWER_QUESTION_FOR_GAME_CLODED);
		}
		
		Long newScoreGame = gameEntity.getScore();
		
		if(newScoreGame == null) {
			newScoreGame = 0L;
		}
		
		answerDTO.setScoreGame(newScoreGame);
		
		UserEntity userEntity = this.findById(gameEntity.getId()).getUser();
		Long newScoreUser = userEntity.getScore();
		
		if(newScoreUser == null) {
			newScoreUser = 0L;
		}
		
		answerDTO.setScoreUser(newScoreUser);
						
		if(answerDTO.isBlAnswer()) {		
									
			gameEntity.setScore(answerDTO.getScoreGame() + 1L);			
			this.save(gameEntity);
			answerDTO.setScoreGame(gameEntity.getScore());			
										
			userEntity.setScore(answerDTO.getScoreUser() + 1L);
			this.userService.save(userEntity);
			answerDTO.setScoreUser(userEntity.getScore());
			
		}
		
		return answerDTO;
	}
	
	public List<RankingDTO> ranking() {
		return userService.findAll();
	}
	
	public GameEntity save(GameEntity gameEntity) {
		return this.gameRepository.save(gameEntity);		
	}
}
