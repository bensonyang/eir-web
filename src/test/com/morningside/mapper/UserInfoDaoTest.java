package com.morningside.mapper;

import com.morningside.utils.BaseTest;
import com.morningsidevc.dao.UserInfoDao;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author float.lu
 */
public class UserInfoDaoTest extends BaseTest {

    @Resource
    private UserInfoDao userInfoDao;

    @Test
    public void find(){
        userInfoDao.findById(1);
    }
}
