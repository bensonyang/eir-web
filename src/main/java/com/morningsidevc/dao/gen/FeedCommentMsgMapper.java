package com.morningsidevc.dao.gen;

import com.morningsidevc.po.gen.FeedCommentMsg;
import com.morningsidevc.po.gen.FeedCommentMsgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FeedCommentMsgMapper {
    int countByExample(FeedCommentMsgExample example);

    int deleteByExample(FeedCommentMsgExample example);

    int deleteByPrimaryKey(Integer commentid);

    int insert(FeedCommentMsg record);

    int insertSelective(FeedCommentMsg record);

    List<FeedCommentMsg> selectByExample(FeedCommentMsgExample example);

    FeedCommentMsg selectByPrimaryKey(Integer commentid);

    int updateByExampleSelective(@Param("record") FeedCommentMsg record, @Param("example") FeedCommentMsgExample example);

    int updateByExample(@Param("record") FeedCommentMsg record, @Param("example") FeedCommentMsgExample example);

    int updateByPrimaryKeySelective(FeedCommentMsg record);

    int updateByPrimaryKey(FeedCommentMsg record);
}