package com.morningsidevc.dao.gen;

import com.morningsidevc.po.gen.WeixinUserMapping;
import com.morningsidevc.po.gen.WeixinUserMappingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WeixinUserMappingMapper {
    int countByExample(WeixinUserMappingExample example);

    int deleteByExample(WeixinUserMappingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WeixinUserMapping record);

    int insertSelective(WeixinUserMapping record);

    List<WeixinUserMapping> selectByExample(WeixinUserMappingExample example);

    WeixinUserMapping selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WeixinUserMapping record, @Param("example") WeixinUserMappingExample example);

    int updateByExample(@Param("record") WeixinUserMapping record, @Param("example") WeixinUserMappingExample example);

    int updateByPrimaryKeySelective(WeixinUserMapping record);

    int updateByPrimaryKey(WeixinUserMapping record);
}