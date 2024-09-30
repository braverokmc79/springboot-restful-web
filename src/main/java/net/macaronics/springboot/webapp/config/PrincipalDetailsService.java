package net.macaronics.springboot.webapp.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.macaronics.springboot.webapp.entity.User;
import net.macaronics.springboot.webapp.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class PrincipalDetailsService implements UserDetailsService {

    // UserRepository를 주입받기 위한 final 필드 선언 (의존성 주입)
    private final UserRepository userRepository;

    // UserDetailsService 인터페이스의 메서드를 구현하여 사용자 정보를 로드하는 역할을 함
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username을 기반으로 UserRepository에서 사용자 정보를 조회
        User user = userRepository.findByUsername(username);
        
        // 조회된 사용자가 없을 경우 UsernameNotFoundException을 발생시킴
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        // 조회된 사용자가 있을 경우, PrincipalDetails 객체로 반환
        return new PrincipalDetails(user);
    }
}
