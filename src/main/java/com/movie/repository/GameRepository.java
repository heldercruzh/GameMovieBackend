package com.movie.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.model.entity.GameEntity;
import com.movie.model.entity.UserEntity;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {

	Optional<GameEntity> findByUserAndBlOpen(UserEntity userEntity, boolean blOpen);
	
}
