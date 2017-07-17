package com.github.crystal.chapter04;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

public class ConfigurationCreateTest {

	@Test
	public void test() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:chapter04/shiro-config.ini");  
		SecurityManager securityManager = factory.getInstance();  
		  
		//将SecurityManager设置到SecurityUtils 方便全局使用  
		SecurityUtils.setSecurityManager(securityManager);  
		Subject subject = SecurityUtils.getSubject();  
		UsernamePasswordToken token = new UsernamePasswordToken("crystal", "123");  
		subject.login(token);  
		  
		Assert.assertTrue(subject.isAuthenticated());  
	}
}