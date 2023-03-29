package com.movie.model.entity;

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
import javax.persistence.Table;

import com.movie.constants.Constants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "tb_game")
public class GameEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_user", nullable=false)
	private UserEntity user;
	
	@Column(name = "score")
	private Long score;
	
	@Column(name = "bl_open")
	private boolean blOpen;
	
	@Column(name = "data_game")
	private ZonedDateTime dataGame;
	
	@PrePersist
	private void onCreate() {
		this.dataGame = ZonedDateTime.now(ZoneId.of(Constants.AMERICA_SAO_PAULO));		
	}
	
}
