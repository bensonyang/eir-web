package com.morningsidevc.dao.gen;

import com.morningsidevc.vo.FeedLikeMsg;
import com.morningsidevc.vo.FeedLikeMsgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FeedLikeMsgMapper {
    int countByExample(FeedLikeMsgExample example);

    int deleteByExample(FeedLikeMsgExample example);

    int deleteByPrimaryKey(Integer likeid);

    int insert(FeedLikeMsg record);

    int insertSelective(FeedLikeMsg record);

    List<FeedLikeMsg> selectByExample(FeedLikeMsgExample example);

    FeedLikeMsg selectByPrimaryKey(Integer likeid);

    int updateByExampleSelective(@Param("record") FeedLikeMsg record, @Param("example") FeedLikeMsgExample example);

    int updateByExample(@Param("record") FeedLikeMsg record, @Param("example") FeedLikeMsgExample example);

    int updateByPrimaryKeySelective(FeedLikeMsg record);

    int updateByPrimaryKey(FeedLikeMsg record);
}