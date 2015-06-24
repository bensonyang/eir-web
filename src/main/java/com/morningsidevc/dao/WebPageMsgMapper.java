package com.morningsidevc.dao;

import com.morningsidevc.vo.WebPageMsg;
import com.morningsidevc.vo.WebPageMsgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WebPageMsgMapper {
    int countByExample(WebPageMsgExample example);

    int deleteByExample(WebPageMsgExample example);

    int deleteByPrimaryKey(Integer msgid);

    int insert(WebPageMsg record);

    int insertSelective(WebPageMsg record);

    List<WebPageMsg> selectByExample(WebPageMsgExample example);

    WebPageMsg selectByPrimaryKey(Integer msgid);

    int updateByExampleSelective(@Param("record") WebPageMsg record, @Param("example") WebPageMsgExample example);

    int updateByExample(@Param("record") WebPageMsg record, @Param("example") WebPageMsgExample example);

    int updateByPrimaryKeySelective(WebPageMsg record);

    int updateByPrimaryKey(WebPageMsg record);
}