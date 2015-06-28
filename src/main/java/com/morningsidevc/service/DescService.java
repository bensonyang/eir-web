package com.morningsidevc.service;

import com.morningsidevc.po.DescBean;

/**
 * @author float.lu
 */
public interface DescService {

    void save(DescBean descBean);
    
    DescBean load(Integer id);
}
