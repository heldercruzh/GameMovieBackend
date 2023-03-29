package com.movie.model.entity;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.movie.constants.Constants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "tb_question")
public class QuestionEntity implements Serializable{
	
	private static final long serialVersionUID = -7025411690240812664L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_game", nullable=false)
	private GameEntity game;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_first_movie", nullable=false)
	private MovieEntity firstMovie;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_second_movie", nullable=false)
	private MovieEntity secondMovie;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_answer_movie")
	private MovieEntity answerMovie;
	
	@Column(name = "data_atualizacao", nullable = false)
	private ZonedDateTime dataAtualizacao;
	
	@PrePersist
	private void onCreate() {
		this.dataAtualizacao = ZonedDateTime.now(ZoneId.of(Constants.AMERICA_SAO_PAULO));		
	}

	@PreUpdate
	private void onUpdate() {
		this.dataAtualizacao = ZonedDateTime.now(ZoneId.of(Constants.AMERICA_SAO_PAULO));
	}
}
