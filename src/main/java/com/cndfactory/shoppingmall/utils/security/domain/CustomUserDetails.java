package com.cndfactory.shoppingmall.utils.security.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {

	private String username;
	private String password;

	private boolean isAccountNonExpired;
	private boolean isAccountNonLocked;
	private boolean isCredentialsNonExpired;
	private boolean isEnabled;

	private Collection<? extends GrantedAuthority> authorities;

	public static CustomUserDetails setAnonymousUser() {
		return new CustomUserDetails();
	}

	@Builder
	public CustomUserDetails(String username, String password, 
				boolean isAccountNonExpired, boolean isAccountNonLocked, 
				boolean isCredentialsNonExpired, boolean isEnabled, String authority) {

		this.username = username;
		this.password = password;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(authority));
		this.authorities = authorities;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CustomUserDetails)) return false;

		CustomUserDetails that = (CustomUserDetails) o;
		return username.equals(that.username) && password.equals(that.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(username, password);
	}
}
