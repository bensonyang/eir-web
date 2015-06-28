package com.morningsidevc.service.impl;

import com.morningsidevc.dao.gen.DescBeanMapper;
import com.morningsidevc.po.DescBean;
import com.morningsidevc.service.DescService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author float.lu
 */
@Component
public class DescServiceImpl implements DescService {

    @Resource
    private DescBeanMapper descBeanMapper;

    public void save(DescBean descBean) {
    	descBeanMapper.insert(descBean);
    }

	@Override
	public DescBean load(Integer id) {
		return descBeanMapper.selectByPrimaryKey(id);
	}
}
