package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.SharkTime;
import com.idealighter.game.dao.dic.po.SharkTimeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SharkTimeMapper {
  long countByExample(SharkTimeExample example);

  int deleteByExample(SharkTimeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(SharkTime record);

  int insertSelective(SharkTime record);

  List<SharkTime> selectByExample(SharkTimeExample example);

  SharkTime selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") SharkTime record,
      @Param("example") SharkTimeExample example);

  int updateByExample(@Param("record") SharkTime record,
      @Param("example") SharkTimeExample example);

  int updateByPrimaryKeySelective(SharkTime record);

  int updateByPrimaryKey(SharkTime record);
}
