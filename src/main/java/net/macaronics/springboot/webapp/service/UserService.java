package net.macaronics.springboot.webapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.macaronics.springboot.webapp.dto.user.UserRegisterFormDTO;
import net.macaronics.springboot.webapp.dto.user.UserResponse;
import net.macaronics.springboot.webapp.dto.user.UserUpdateFormDTO;
import net.macaronics.springboot.webapp.entity.User;
import net.macaronics.springboot.webapp.exception.ResourceNotFoundException;
import net.macaronics.springboot.webapp.repository.TodoRepository;
import net.macaronics.springboot.webapp.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {


    private final UserRepository userRepository;  // User 정보를 관리하는 Repository
    
    private final PasswordEncoder passwordEncoder;  // 비밀번호를 암호화하기 위한 PasswordEncoder
 
    private final TodoRepository todoRepository;
    
        
    /**
     * 사용자를 저장하는 메서드 
     * @param user
     * @return
     */
    public User saveUser(User user) {
        // 비밀번호를 암호화하여 저장
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser=userRepository.save(user);  // 사용자 정보 저장        
        return  savedUser;
    }

    
    /**
     * 사용자 이름으로 사용자 정보를 조회하는 메서드
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);  // 사용자 이름으로 조회 후 반환
    }

    
    /**
     * 사유저 목록 불러오기
     * @param pageable
     * @return
     */
	public Page<UserResponse> userListAll(PageRequest pageable) {
		Page<UserResponse> userList = userRepository.findAll(pageable).map(UserResponse::of);
		return userList;
	}

	
	/**
	 * 사용자를 찾는 메서드
	 * @param id
	 * @return
	 */
	public UserResponse getUserById(Long id) {
		User user= userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id+" 을 찾을 수 없습니다."));		
		return UserResponse.of(user); 		 
	}


	/**
	 * 사용자 삭제		
	 * @param id
	 */
	public void deleteUser(Long id) {
		User user= userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id+" 을 사용자를 찾을 수 없습니다."));			
        todoRepository.deleteByUserId(user.getId());        
		userRepository.delete(user);		
	}

	
	/**
	 * 더티체킹 업데이트
	 * @param id
	 * @param updateFormDTO
	 * @return
	 */
	public UserResponse updateUser(Long id, UserUpdateFormDTO updateFormDTO) {
		User user=userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id+" 을 사용자를 찾을 수 없습니다."));
		
		//더티 체킹
		User updated=User.updateUser(user, updateFormDTO);
		
		return UserResponse.of(updated); 	
	}
	
	
	
	
	
	
	
}
