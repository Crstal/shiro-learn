package com.github.crystal.chapter06.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

import com.github.crystal.chapter06.entity.User;

public class MyRealm3 implements Realm {
	public String getName() {
		return "c";
	}

	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		User user = new User("crystal", "123");  
		return new SimpleAuthenticationInfo(user, "123", getName());
	}
}
