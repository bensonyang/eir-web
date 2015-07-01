package com.morningsidevc.dao.gen;

import com.morningsidevc.po.gen.UserFeedCounter;
import com.morningsidevc.po.gen.UserFeedCounterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserFeedCounterMapper {
    int countByExample(UserFeedCounterExample example);

    int deleteByExample(UserFeedCounterExample example);

    int deleteByPrimaryKey(Integer counterid);

    int insert(UserFeedCounter record);

    int insertSelective(UserFeedCounter record);

    List<UserFeedCounter> selectByExample(UserFeedCounterExample example);

    UserFeedCounter selectByPrimaryKey(Integer counterid);

    int updateByExampleSelective(@Param("record") UserFeedCounter record, @Param("example") UserFeedCounterExample example);

    int updateByExample(@Param("record") UserFeedCounter record, @Param("example") UserFeedCounterExample example);

    int updateByPrimaryKeySelective(UserFeedCounter record);

    int updateByPrimaryKey(UserFeedCounter record);
}