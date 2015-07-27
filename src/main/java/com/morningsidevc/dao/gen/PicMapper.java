package com.morningsidevc.dao.gen;

import com.morningsidevc.po.gen.Pic;
import com.morningsidevc.po.gen.PicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PicMapper {
    int countByExample(PicExample example);

    int deleteByExample(PicExample example);

    int deleteByPrimaryKey(Integer picid);

    int insert(Pic record);

    int insertSelective(Pic record);

    List<Pic> selectByExampleWithBLOBs(PicExample example);

    List<Pic> selectByExample(PicExample example);

    Pic selectByPrimaryKey(Integer picid);

    int updateByExampleSelective(@Param("record") Pic record, @Param("example") PicExample example);

    int updateByExampleWithBLOBs(@Param("record") Pic record, @Param("example") PicExample example);

    int updateByExample(@Param("record") Pic record, @Param("example") PicExample example);

    int updateByPrimaryKeySelective(Pic record);

    int updateByPrimaryKeyWithBLOBs(Pic record);
}