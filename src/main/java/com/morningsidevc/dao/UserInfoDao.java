package com.morningsidevc.dao;

import com.morningsidevc.dao.gen.UserInfoMapper;
import com.morningsidevc.po.UserInfo;

/**
 * @author float.lu
 */
public interface UserInfoDao {
    UserInfo findById(int id);
}
