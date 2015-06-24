package com.morningsidevc.dao;

import com.morningsidevc.vo.WeixinUserInfo;
import com.morningsidevc.vo.WeixinUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WeixinUserInfoMapper {
    int countByExample(WeixinUserInfoExample example);

    int deleteByExample(WeixinUserInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WeixinUserInfo record);

    int insertSelective(WeixinUserInfo record);

    List<WeixinUserInfo> selectByExample(WeixinUserInfoExample example);

    WeixinUserInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WeixinUserInfo record, @Param("example") WeixinUserInfoExample example);

    int updateByExample(@Param("record") WeixinUserInfo record, @Param("example") WeixinUserInfoExample example);

    int updateByPrimaryKeySelective(WeixinUserInfo record);

    int updateByPrimaryKey(WeixinUserInfo record);
}