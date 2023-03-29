package com.movie.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessage {

	public static final String NON_EXISTENT_MOVIE = "Non-existent movie.";
	public static final String NON_EXISTENT_USER = "Non-existent user.";
	public static final String NON_EXISTENT_GAME = "Non-existent game.";
	public static final String NON_EXISTENT_QUESTION = "Non-existent question.";
	public static final String EMAIL_ALREADY_REGISTERED = "There is already a user registered with that e-mail.";
	public static final String UNABLE_PROVIDE_QUESTION_FOR_GAME_CLODED = "Unable to provide questions for a closed game.";
	public static final String UNABLE_PROVIDE_ANSWER_QUESTION_FOR_GAME_CLODED = "Unable to answer questions in closed game.";
	public static final String NO_MORE_QUESTIONS_AVAILABLE = "The game will exit as there are no more movies available.";
	public static final String QUESTION_ALREADY_ANSWERED = "Question already answered.";
	public static final String CHOSE_MOVIE_DIREFENTE = "You can only choose one of the provided movies.";
}
