package net.macaronics.springboot.webapp.service;

import net.macaronics.springboot.webapp.dto.UserResponse;
import net.macaronics.springboot.webapp.entity.User;
import net.macaronics.springboot.webapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;  // User 정보를 관리하는 Repository

    @Autowired
    private PasswordEncoder passwordEncoder;  // 비밀번호를 암호화하기 위한 PasswordEncoder

    // 사용자를 저장하는 메서드
    public void saveUser(User user) {
        // 비밀번호를 암호화하여 저장
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);  // 사용자 정보 저장
    }

    // 사용자 이름으로 사용자 정보를 조회하는 메서드
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);  // 사용자 이름으로 조회 후 반환
    }

    
    // 유저 목록 불러오기
	public Page<UserResponse> userListAll(PageRequest pageable) {
		Page<UserResponse> userList = userRepository.findAll(pageable).map(UserResponse::of);
		return userList;
	}
	
	
	
}
