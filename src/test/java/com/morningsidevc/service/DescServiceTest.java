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

import com.morningsidevc.vo.DescBean;

/**
 * @author yangna
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-*.xml")
public class DescServiceTest extends AbstractJUnit4SpringContextTests {

	@Resource
	private DescService descService;
	
	@Test
	public void saveTest() {
		DescBean descBean = new DescBean();
		descBean.setName("World");
		descService.save(descBean);
	}
	
}
