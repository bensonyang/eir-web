package com.morningsidevc.service.impl;

import com.morningsidevc.dao.DescBeanMapper;
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

    @Override
    public void save(DescBean descBean) {
        descDao.insert(descBean);
    }
}
