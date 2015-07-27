package com.morningsidevc.service;

import com.morningsidevc.po.gen.Pic;

/**
 * @author float.lu
 */
public interface PicService {
    public Pic loadPic(Integer picId);
    public Integer insertPic(Pic pic);
}
