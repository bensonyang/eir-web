package com.morningside.mapper;

import com.morningside.utils.BaseTest;
import com.morningsidevc.dao.gen.UserInfoMapper;
import com.morningsidevc.vo.UserInfo;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;

/**
 *
 * @author float.lu
 */
public class UserInfoMapperTest extends BaseTest {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Test
    public void insert(){
        UserInfo userInfo = new UserInfo();
        userInfo.setNickname("float.lu");
        userInfo.setAddtime(new Date());
        userInfo.setLasttime(new Date());
        userInfoMapper.insert(userInfo);
    }
}
