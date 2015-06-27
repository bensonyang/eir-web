package com.morningsidevc.dao;

import com.morningsidevc.po.gen.UserInfo;

/**
 * @author float.lu
 */
public interface UserInfoDao {
    UserInfo findById(int id);
}
