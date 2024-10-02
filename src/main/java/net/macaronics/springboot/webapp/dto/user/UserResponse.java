package net.macaronics.springboot.webapp.dto.user;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.macaronics.springboot.webapp.entity.User;
import net.macaronics.springboot.webapp.enums.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse extends EntityModel<UserResponse> {

	private Long id;

	//@JsonProperty("name")
	private String username;

	private LocalDate birthDate;

	private Role role;

	private static ModelMapper modelMapper = new ModelMapper();

	public static UserResponse of(User user) {
		return modelMapper.map(user, UserResponse.class);
	}

}
