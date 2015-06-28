package com.morningsidevc.dao.gen;

import com.morningsidevc.po.WeiboMsg;
import com.morningsidevc.po.WeiboMsgExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WeiboMsgMapper {
    int countByExample(WeiboMsgExample example);

    int deleteByExample(WeiboMsgExample example);

    int deleteByPrimaryKey(Integer msgid);

    int insert(WeiboMsg record);

    int insertSelective(WeiboMsg record);

    List<WeiboMsg> selectByExample(WeiboMsgExample example);

    WeiboMsg selectByPrimaryKey(Integer msgid);

    int updateByExampleSelective(@Param("record") WeiboMsg record, @Param("example") WeiboMsgExample example);

    int updateByExample(@Param("record") WeiboMsg record, @Param("example") WeiboMsgExample example);

    int updateByPrimaryKeySelective(WeiboMsg record);

    int updateByPrimaryKey(WeiboMsg record);
}