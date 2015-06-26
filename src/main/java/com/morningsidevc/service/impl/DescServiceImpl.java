package com.morningsidevc.service.impl;

import com.morningsidevc.dao.gen.DescBeanMapper;
import com.morningsidevc.service.DescService;
import com.morningsidevc.vo.DescBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author float.lu
 */
@Component
public class DescServiceImpl implements DescService {

    @Resource
    private DescBeanMapper descDao;

    public void save(DescBean descBean) {

    }

	@Override
	public DescBean load(Integer id) {
		return descDao.selectByPrimaryKey(id);
	}
}
