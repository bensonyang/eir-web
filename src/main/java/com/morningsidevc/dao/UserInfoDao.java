package com.morningsidevc.dao;

import com.morningsidevc.vo.UserInfo;

/**
 * @author float.lu
 */
public interface UserInfoDao {
    UserInfo findById(int id);
}
