package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.DdzTime;
import com.idealighter.game.dao.dic.po.DdzTimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DdzTimeMapper {
    long countByExample(DdzTimeExample example);

    int deleteByExample(DdzTimeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DdzTime record);

    int insertSelective(DdzTime record);

    List<DdzTime> selectByExample(DdzTimeExample example);

    DdzTime selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DdzTime record, @Param("example") DdzTimeExample example);

    int updateByExample(@Param("record") DdzTime record, @Param("example") DdzTimeExample example);

    int updateByPrimaryKeySelective(DdzTime record);

    int updateByPrimaryKey(DdzTime record);
}