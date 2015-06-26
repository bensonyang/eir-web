package com.morningside.utils;

import com.morningsidevc.dao.gen.DescBeanMapper;
import com.morningsidevc.vo.DescBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author float.lu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/spring-*.xml"})
public class DescServiceTest {
    @Resource
    private DescBeanMapper descMapper;

    @Test
    public void save(){
        DescBean desc = new DescBean();
        desc.setName("desc");
        descMapper.insert(desc);

    }
}
