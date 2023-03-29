package com.movie.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "tb_movie")
public class MovieEntity implements Serializable{
	
	private static final long serialVersionUID = -7025411690240812664L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "score", nullable = false)
	private BigDecimal score;
	
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
