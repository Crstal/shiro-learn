package com.github.crystal.chapter02.authenticator.strategy;

import java.util.Collection;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.pam.AbstractAuthenticationStrategy;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.CollectionUtils;

/**
 * 自己实现验证逻辑
 * @author cye
 *
 */
public class AtLeastTwoAuthenticatorStrategy extends AbstractAuthenticationStrategy {

	/**
	 * 在所有Realm验证之前调用 
	 */
	@Override
	public AuthenticationInfo beforeAllAttempts(
			Collection<? extends Realm> realms, AuthenticationToken token)
			throws AuthenticationException {
		return super.beforeAllAttempts(realms, token);
	}

	/**
	 * 在每个Realm之前调用 
	 */
	@Override
	public AuthenticationInfo beforeAttempt(Realm realm,
			AuthenticationToken token, AuthenticationInfo aggregate)
			throws AuthenticationException {
		return super.beforeAttempt(realm, token, aggregate);
	}

	/**
	 * 在每个Realm之后调用  
	 */
	@Override
	public AuthenticationInfo afterAttempt(Realm realm,
			AuthenticationToken token, AuthenticationInfo singleRealmInfo,
			AuthenticationInfo aggregateInfo, Throwable t)
			throws AuthenticationException {
		return super.afterAttempt(realm, token, singleRealmInfo, aggregateInfo, t);
	}

	/**
	 * 在所有Realm之后调用 
	 */
	@Override
	public AuthenticationInfo afterAllAttempts(AuthenticationToken token,
			AuthenticationInfo aggregate) throws AuthenticationException {
		if (aggregate == null || CollectionUtils.isEmpty(aggregate.getPrincipals()) || aggregate.getPrincipals().getRealmNames().size() < 2) {
            throw new AuthenticationException("Authentication token of type [" + token.getClass() + "] " +
                    "could not be authenticated by any configured realms.  Please ensure that at least two realm can " +
                    "authenticate these tokens.");
        }

        return aggregate;
	}

}
