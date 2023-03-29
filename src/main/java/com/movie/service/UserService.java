package com.movie.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.movie.constants.ErrorMessage;
import com.movie.enumerators.ProfileEnum;
import com.movie.exceptions.MessageException;
import com.movie.exceptions.NotFoundException;
import com.movie.model.dto.RankingDTO;
import com.movie.model.dto.UserDTO;
import com.movie.model.entity.UserEntity;
import com.movie.model.mapper.UserMapper;
import com.movie.model.validators.LoginVoValidator;
import com.movie.model.validators.UserVoValidator;
import com.movie.model.vo.LoginVO;
import com.movie.model.vo.UserVO;
import com.movie.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
		
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final LoginVoValidator loginVoValidator;
	private final UserVoValidator userVoValidator;
	
	public UserDTO login(LoginVO loginVO) {
		
		this.loginVoValidator.run(loginVO);
		
		Optional<UserEntity> optionalUserEntity = userRepository.findByEmailAndPassword(loginVO.getEmail(), loginVO.getPassword());
			
		if(!optionalUserEntity.isPresent()) {
			throw new NotFoundException(ErrorMessage.NON_EXISTENT_USER);
		}
	
		return userMapper.toDTO(optionalUserEntity.get());
	
	}

	public UserDTO insert(UserVO userVO) {
		
		this.userVoValidator.run(userVO);
		
		this.validEmailAlreadyExist(userVO.getId(), userVO.getEmail());
				
		UserEntity userEntity = userMapper.toEntity(userVO);
		userEntity.setIdProfile(ProfileEnum.GAMER.getId());
				
		return userMapper.toDTO(this.save(userEntity));
	}

	public UserDTO update(UserVO userVO) {
		
		this.userVoValidator.run(userVO);
		
		this.validEmailAlreadyExist(userVO.getId(), userVO.getEmail());
				
		UserEntity userEntity = this.findById(userVO.getId());
		userEntity.setEmail(userVO.getEmail());
		userEntity.setName(userVO.getName());
		userEntity.setPassword(userVO.getPassword());
		
		return userMapper.toDTO(this.save(userEntity));
	}
	
	public UserEntity save(UserEntity userEntity) {
		return userRepository.save(userEntity);		
	}
	
	private void validEmailAlreadyExist(Long id, String email) {
		
		if(id == null) {
			if(userRepository.existsByEmail(email)) {
				throw new MessageException(ErrorMessage.EMAIL_ALREADY_REGISTERED);
			}	
			return;
		}
		
		Optional<UserEntity> optionalUserEntityEmail = userRepository.findByEmail(email);
		
		if(!optionalUserEntityEmail.isPresent()) {
			return;
		}
		
		if(!optionalUserEntityEmail.get().getId().equals(id)) {
			throw new MessageException(ErrorMessage.EMAIL_ALREADY_REGISTERED);
		}
				
	}
		
	public UserDTO findDtoById(Long id) {		
		return userMapper.toDTO( 
					userRepository.findById(id)
					.orElseThrow(() -> new NotFoundException(ErrorMessage.NON_EXISTENT_USER))
			    );
	}
	
	public UserEntity findById(Long id) {		
		return userRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.NON_EXISTENT_USER));
	}
	
	public List<RankingDTO> findAll() {
		return this.toListDTO(userRepository.findByOrderByScoreDesc());
	}
	
	private List<RankingDTO> toListDTO (List<UserEntity> listEntity) {
    	Long sequence = 1L;
    	Long lastScore = 0L;
    	
    	List<RankingDTO> listDto = new ArrayList<>();
    	
    	for(UserEntity entity: listEntity) {
    		RankingDTO dto = new RankingDTO();
			 
			 dto.setName(entity.getName());
			 dto.setScore(entity.getScore());
			 
			 if(lastScore > entity.getScore()) {
			      lastScore = entity.getScore();      
			      sequence++;
			  } else {
			      lastScore = entity.getScore();
			  }
			 
			 
			 dto.setPosicao(sequence);
			 
			 listDto.add(dto);
			
    	}
    	
    	return listDto;
    	
    } 

}
