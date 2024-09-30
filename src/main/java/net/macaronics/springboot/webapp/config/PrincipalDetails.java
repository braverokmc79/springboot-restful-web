package net.macaronics.springboot.webapp.config;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import net.macaronics.springboot.webapp.entity.User;
import net.macaronics.springboot.webapp.enums.Role;

import lombok.Getter;

@Getter
public class PrincipalDetails implements UserDetails {

	@Serial
	private static final long serialVersionUID = 1L;
	private final User user;
	private final long id;
	private final String username;
	private final Role role;

	public PrincipalDetails(User user) {
		this.user = user;
		this.id = user.getId();
		this.username = user.getUsername();
		this.role = user.getRole();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collector = new ArrayList<>();
		collector.add(() -> user.getRole().toString());
		return collector;
	}

	/**
	 * 사용자를 인증하는 데 사용된 암호를 반환합니다.
	 */
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	/**
	 * 사용자를 인증하는 데 사용된 사용자 이름을 반환합니다. null을 반환할 수 없습니다.
	 */
	@Override
	public String getUsername() {
		return user.getUsername();
	}

	/**
	 * 사용자의 계정이 만료되었는지 여부를 나타냅니다. 만료된 계정은 인증할 수 없습니다.
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 사용자가 잠겨 있는지 또는 잠금 해제되어 있는지 나타냅니다. 잠긴 사용자는 인증할 수 없습니다.
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 사용자의 자격 증명(암호)이 만료되었는지 여부를 나타냅니다. 만료된 자격 증명은 인증을 방지합니다.
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 사용자가 활성화되었는지 비활성화되었는지 여부를 나타냅니다. 비활성화된 사용자는 인증할 수 없습니다.
	 */
	@Override
	public boolean isEnabled() {
		// 우리 사이트 1년동안 회원이 로그인을 안하면!! 휴먼 계정으로 하기로 함.
		// 현재시간-로긴시간=>1년을 초과하면 return false;
		return true;
	}

}
