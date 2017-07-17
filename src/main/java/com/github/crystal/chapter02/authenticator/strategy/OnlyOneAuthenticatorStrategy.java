package com.github.crystal.chapter02.authenticator.strategy;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.AbstractAuthenticationStrategy;
import org.apache.shiro.realm.Realm;

public class OnlyOneAuthenticatorStrategy extends AbstractAuthenticationStrategy {

	@Override
	public AuthenticationInfo afterAttempt(Realm realm,
			AuthenticationToken token, AuthenticationInfo singleRealmInfo,
			AuthenticationInfo aggregateInfo, Throwable t)
			throws AuthenticationException {
		AuthenticationInfo info = null;
		if (singleRealmInfo == null) {
			info = aggregateInfo;
		} else {
			if (aggregateInfo == null) {
				info = singleRealmInfo;
			} else {
				info = merge(singleRealmInfo, aggregateInfo);
                if(info.getPrincipals().getRealmNames().size() > 1) {
                    System.out.println(info.getPrincipals().getRealmNames());
                    throw new AuthenticationException("Authentication token of type [" + token.getClass() + "] " +
                            "could not be authenticated by any configured realms.  Please ensure that only one realm can " +
                            "authenticate these tokens.");
                }
			}
		}
		return info;
	}
}
