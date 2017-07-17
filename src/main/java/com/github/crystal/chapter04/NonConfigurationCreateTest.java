package com.github.crystal.chapter04;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.crystal.chapter03.permission.BitAndWildPermissionResolver;

public class NonConfigurationCreateTest {

	@Test
	public void test() {
		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		
		//设置authenticator
		ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
		authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
		securityManager.setAuthenticator(authenticator);
		
		//设置authorizer
		ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
		authorizer.setPermissionResolver(new BitAndWildPermissionResolver());
		securityManager.setAuthorizer(authorizer);
		
		//设置realm
		DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/shiro");
        ds.setUsername("root");
        ds.setPassword("12345");
        
        JdbcRealm jdbc = new JdbcRealm();
        jdbc.setDataSource(ds);
        securityManager.setRealm(jdbc);
        
        //将SecurityManager设置到SecurityUtils 方便全局使用
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("crystal", "123");
        subject.login(token);

        Assert.assertTrue(subject.isAuthenticated());
	}
}
