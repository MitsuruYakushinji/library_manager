package com.example.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.entity.User;

public class LoginUser implements UserDetails{
	
	// Userオブジェクト(Entityクラス)
	private final User user;
	
	// コンストラクタ(userをセット)
	public LoginUser(User user) {
		this.user = user;
	}
	// Userオブジェクトのゲッター
	public User getUser() {
		return this.user;
	}
	
	// ユーザ認証に使用されるパスワードを返す
	@Override
	public String getPassword() {
		return this.user.getPassword();
	}
	
	// ユーザ認証に使用するユーザ名(今回はメールアドレス)
	@Override
	public String getUsername() {
		return this.user.getEmail();
	}

	// ユーザーに付与された権限を返却
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.NO_AUTHORITIES;
	}
	
	// アカウントの有効期限の状態を判定する
	@Override
	public boolean isAccountNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	// アカウントのロック状態を判定する
	@Override
	public boolean isAccountNonLocked() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	// 資格情報の有効期限の状態を判定する
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	// 有効なユーザかを判定する
	@Override
	public boolean isEnabled() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

}
