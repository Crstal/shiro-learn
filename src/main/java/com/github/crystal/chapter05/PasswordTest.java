package com.github.crystal.chapter05;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.junit.Test;

import com.github.crystal.chapter03.BaseTest;

public class PasswordTest extends BaseTest {
	
	@Test
	public void testPasswordServiceWithMyRealm() {
		login("classpath:chapter05/shiro-passwordservice.ini", "crystal", "123");
	}
	
	@Test
	public void testJdbcPasswordServiceWithMyRealm() {
		login("classpath:chapter05/shiro-jdbc-passwordservice.ini", "karry", "123");
	}
	
	@Test
	public void testGeneratePassword() {
		String algorithmName = "md5";
		String username = "crystal";
		String password = "123";
		String salt1 = username;
		String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
		int hashIterations = 2;
		
		SimpleHash simpleHash = new SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);
		String encodedPassword  = simpleHash.toHex();
		System.out.println(salt2);
		System.out.println(encodedPassword);
	}
	
	@Test
	public void testHashedCredentialsMatcherWithMyRealm2() {
		login("classpath:chapter05/shiro-hashedCredentialsMatcher.ini", "crystal", "123");//使用testGeneratePassword生成的散列密码
	}
	
	private class EnumConverter extends AbstractConverter {
        @Override
        protected String convertToString(final Object value) throws Throwable {
            return ((Enum) value).name();
        }
        @SuppressWarnings("unchecked")
		@Override
        protected Object convertToType(final Class type, final Object value) throws Throwable {
            return Enum.valueOf(type, value.toString());
        }

        @SuppressWarnings("unchecked")
		@Override
        protected Class getDefaultType() {
            return null;
        }
    }
	
	@Test
	public void testJdbcHashedCredentialsMatcherWithMyRealm2() {
		BeanUtilsBean.getInstance().getConvertUtils().register(new EnumConverter(), JdbcRealm.SaltStyle.class);

		login("classpath:chapter05/shiro-jdbc-hashedCredentialsMatcher.ini", "crystal", "123");//使用testGeneratePassword生成的散列密码
	}
	
	@Test
    public void testRetryLimitHashedCredentialsMatcherWithMyRealm() {
        for(int i = 1; i <= 4; i++) {
            try {
                login("classpath:chapter05/shiro-retryLimitHashedCredentialsMatcher.ini", "crystal", "234");
            } catch (Exception e) {/*ignore*/}
        }
        login("classpath:chapter05/shiro-retryLimitHashedCredentialsMatcher.ini", "crystal", "123");
    }
}
