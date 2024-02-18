package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.BairenniuniuTime;
import com.idealighter.game.dao.dic.po.BairenniuniuTimeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BairenniuniuTimeMapper {
  long countByExample(BairenniuniuTimeExample example);

  int deleteByExample(BairenniuniuTimeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(BairenniuniuTime record);

  int insertSelective(BairenniuniuTime record);

  List<BairenniuniuTime> selectByExample(BairenniuniuTimeExample example);

  BairenniuniuTime selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") BairenniuniuTime record,
      @Param("example") BairenniuniuTimeExample example);

  int updateByExample(@Param("record") BairenniuniuTime record,
      @Param("example") BairenniuniuTimeExample example);

  int updateByPrimaryKeySelective(BairenniuniuTime record);

  int updateByPrimaryKey(BairenniuniuTime record);
}
