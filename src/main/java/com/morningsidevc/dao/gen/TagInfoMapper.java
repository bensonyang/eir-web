package com.morningsidevc.dao.gen;

import com.morningsidevc.po.gen.TagInfo;
import com.morningsidevc.po.gen.TagInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TagInfoMapper {
    int countByExample(TagInfoExample example);

    int deleteByExample(TagInfoExample example);

    int deleteByPrimaryKey(Integer tagid);

    int insert(TagInfo record);

    int insertSelective(TagInfo record);

    List<TagInfo> selectByExample(TagInfoExample example);

    TagInfo selectByPrimaryKey(Integer tagid);

    int updateByExampleSelective(@Param("record") TagInfo record, @Param("example") TagInfoExample example);

    int updateByExample(@Param("record") TagInfo record, @Param("example") TagInfoExample example);

    int updateByPrimaryKeySelective(TagInfo record);

    int updateByPrimaryKey(TagInfo record);
}