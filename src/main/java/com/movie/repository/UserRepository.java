package com.movie.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.model.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	boolean existsByEmail(String email);
	
	Optional<UserEntity> findByEmail(String email);
	
	Optional<UserEntity> findByEmailAndPassword(String email, String password);
		
	List<UserEntity> findByOrderByScoreDesc();
}
