package com.morningsidevc.dao;

import com.morningsidevc.vo.DescBean;
import com.morningsidevc.vo.DescBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DescBeanMapper {
    int countByExample(DescBeanExample example);

    int deleteByExample(DescBeanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DescBean record);

    int insertSelective(DescBean record);

    List<DescBean> selectByExample(DescBeanExample example);

    DescBean selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DescBean record, @Param("example") DescBeanExample example);

    int updateByExample(@Param("record") DescBean record, @Param("example") DescBeanExample example);

    int updateByPrimaryKeySelective(DescBean record);

    int updateByPrimaryKey(DescBean record);
}