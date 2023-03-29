package com.movie.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.model.dto.AnswerDTO;
import com.movie.model.dto.GameDTO;
import com.movie.model.dto.QuestionDTO;
import com.movie.model.dto.RankingDTO;
import com.movie.model.vo.AnswerVO;
import com.movie.service.GameService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {
	
	private final GameService service;
		
	@ApiOperation(value = "Starts a game round.")
	@GetMapping("/start/{id}")		
	public GameDTO start(@PathVariable Long id) {			
		return service.start(id);		
	}
	
	@ApiOperation(value = "Ends a round of the game.")
	@GetMapping("/stop/{id}")		
	public GameDTO stop(@PathVariable Long id) {		
		return service.stop(id);		
	}
	
	@ApiOperation(value = "Provides new questions for an ongoing match.")
	@GetMapping("/provide-question/{idGame}")		
	public QuestionDTO provideQuestion(@PathVariable Long idGame) {		
		return service.provideQuestion(idGame);		
	}
		
	@ApiOperation(value = "Validates the answer to a given question.")
	@PutMapping("/validate-answer")	
	public AnswerDTO validateAnswer(@RequestBody AnswerVO answerVO) {		
		return service.validateAnswer(answerVO);		
	}
	
	@ApiOperation(value = "Shows users ranking.")
	@GetMapping("/ranking")		
	public List<RankingDTO> ranking() {		
		return service.ranking();		
	}
	
	
	
	
}
