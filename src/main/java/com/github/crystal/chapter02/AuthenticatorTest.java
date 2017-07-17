package com.github.crystal.chapter02;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

public class AuthenticatorTest {

	private void login(String configFile) {
		//1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager  
	    @SuppressWarnings("deprecation")
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);  
	  
	    //2、得到SecurityManager实例 并绑定给SecurityUtils  
	    SecurityManager securityManager = factory.getInstance();  
	    SecurityUtils.setSecurityManager(securityManager);  
	  
	    //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）  
	    Subject subject = SecurityUtils.getSubject();  
	    UsernamePasswordToken token = new UsernamePasswordToken("barry", "123");  
	    subject.login(token);  
	}
	
	@Test
	public void testAllSuccessfulStrategyWithSuccess() {
		login("classpath:chapter02/shiro-authenticator-all-success.ini");  
	    Subject subject = SecurityUtils.getSubject();  
	  
	    //得到一个身份集合，其包含了Realm验证成功的身份信息  
	    PrincipalCollection principalCollection = subject.getPrincipals();  
	    Assert.assertEquals(2, principalCollection.asList().size());  
	}
	
	@Test(expected = UnknownAccountException.class)  
    public void testAllSuccessfulStrategyWithFail() {  
        login("classpath:chapter02/shiro-authenticator-all-fail.ini");  
	}   
	
	@Test
	public void testAtLeastOneSuccessfulStrategyWithSuccess() {  
        login("classpath:chapter02/shiro-authenticator-atLeastOne-success.ini");  
	}   
	
	@Test
	public void testAtLeastTwoSuccessfulStrategyWithSuccess() {  
        login("classpath:chapter02/shiro-authenticator-atLeastTwo-success.ini");  
	}   
	
	@Test
	public void testOnlyOneSuccessfulStrategyWithSuccess() {  
        login("classpath:chapter02/shiro-authenticator-onlyone-success.ini");  
	}   
}
