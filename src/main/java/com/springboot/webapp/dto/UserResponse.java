package com.springboot.webapp.dto;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;

import com.springboot.webapp.entity.User;
import com.springboot.webapp.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

	private Long id;

	private String username;


	private LocalDate birthDate;

	private Role role; 
	
	private static ModelMapper modelMapper =new ModelMapper();
		
	public static UserResponse of(User user) {
		return modelMapper.map(user, UserResponse.class);
	}
	
	
	
}
