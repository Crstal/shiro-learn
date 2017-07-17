package com.github.crystal.chapter03;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

public class RoleTest {
	private void login(String configFile, String username, String password) {
		//1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager  
	    @SuppressWarnings("deprecation")
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);  
	  
	    //2、得到SecurityManager实例 并绑定给SecurityUtils  
	    SecurityManager securityManager = factory.getInstance();  
	    SecurityUtils.setSecurityManager(securityManager);  
	  
	    //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）  
	    Subject subject = SecurityUtils.getSubject();  
	    UsernamePasswordToken token = new UsernamePasswordToken(username, password);  
	    subject.login(token);  
	}
	
	private Subject subject() {
		return SecurityUtils.getSubject();  
	}  
	
	@Test  
	public void testHasRole() {  
	    login("classpath:chapter03/shiro-role.ini", "crystal", "123");  
	    //判断拥有角色：role1  
	    Assert.assertTrue(subject().hasRole("role1"));  
	    //判断拥有角色：role1 and role2  
	    Assert.assertTrue(subject().hasAllRoles(Arrays.asList("role1", "role2")));  
	    //判断拥有角色：role1 and role2 and !role3  
	    boolean[] result = subject().hasRoles(Arrays.asList("role1", "role2", "role3"));  
	    Assert.assertEquals(true, result[0]);  
	    Assert.assertEquals(true, result[1]);  
	    Assert.assertEquals(false, result[2]);  
	}
	
	@Test
	public void testCheckRole() {
		 login("classpath:chapter03/shiro-role.ini", "crystal", "123");  
		 subject().checkRole("role1");//断言拥有角色：role1  
		 subject().checkRoles("role1", "role2"); //断言拥有角色：role1 and role3 失败抛出异常  
	}
}
