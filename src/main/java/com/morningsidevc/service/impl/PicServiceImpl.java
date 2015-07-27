package com.morningsidevc.service.impl;

import com.morningsidevc.dao.gen.PicMapper;
import com.morningsidevc.po.gen.Pic;
import com.morningsidevc.service.PicService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author float.lu
 */
@Component
public class PicServiceImpl implements PicService {
    @Resource
    private PicMapper picMapper;

    @Override
    public Pic loadPic(Integer picId) {
        return picMapper.selectByPrimaryKey(picId);
    }

    @Override
    public Integer insertPic(Pic pic) {
        return picMapper.insert(pic);
    }
}
