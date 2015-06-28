/**
 * 
 */
package com.morningsidevc.service;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.morningsidevc.po.gen.UserInfo;

/**
 * @author yangna
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-*.xml")
public class UserInfoServiceTest extends AbstractJUnit4SpringContextTests {
	
	@Resource
	private UserInfoService userInfoService;
	
	@Test
	public void loadTest() {
		UserInfo userInfo = userInfoService.load(1);
		System.out.println(userInfo);
	}
	
}
