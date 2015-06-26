package com.morningsidevc.dao.gen;

import com.morningsidevc.vo.FeedInfo;
import com.morningsidevc.vo.FeedInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FeedInfoMapper {
    int countByExample(FeedInfoExample example);

    int deleteByExample(FeedInfoExample example);

    int deleteByPrimaryKey(Integer feedid);

    int insert(FeedInfo record);

    int insertSelective(FeedInfo record);

    List<FeedInfo> selectByExample(FeedInfoExample example);

    FeedInfo selectByPrimaryKey(Integer feedid);

    int updateByExampleSelective(@Param("record") FeedInfo record, @Param("example") FeedInfoExample example);

    int updateByExample(@Param("record") FeedInfo record, @Param("example") FeedInfoExample example);

    int updateByPrimaryKeySelective(FeedInfo record);

    int updateByPrimaryKey(FeedInfo record);
}