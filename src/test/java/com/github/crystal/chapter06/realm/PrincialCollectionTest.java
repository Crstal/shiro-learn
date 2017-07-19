package com.github.crystal.chapter06.realm;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import com.github.crystal.chapter03.BaseTest;
import com.github.crystal.chapter06.entity.User;

public class PrincialCollectionTest extends BaseTest {

	@Test
	public void test() {
		//因为Realm里没有进行验证，所以相当于每个Realm都身份验证成功了
		login("classpath:chapter06/shiro-multirealm.ini", "crystal", "123");
		Subject subject = subject();
		//获取Primary Principal（即第一个）
		Object primaryPrincipal1 = subject.getPrincipal();
        PrincipalCollection princialCollection = subject.getPrincipals();
        Object primaryPrincipal2 = princialCollection.getPrimaryPrincipal();
        //但是因为多个Realm都返回了Principal，所以此处到底是哪个是不确定的
        Assert.assertEquals(primaryPrincipal1, primaryPrincipal2);
        
        //返回 a b c
        Set<String> realmNames = princialCollection.getRealmNames();
        System.out.println(realmNames);
        
        //因为MyRealm1和MyRealm2返回的凭据都是crystal，所以排重了
        Set<Object> principals = princialCollection.asSet(); //asList和asSet的结果一样
        System.out.println(principals);
        
        //根据Realm名字获取
        Collection<User> users = princialCollection.fromRealm("c");
        System.out.println(users);
	}
}
