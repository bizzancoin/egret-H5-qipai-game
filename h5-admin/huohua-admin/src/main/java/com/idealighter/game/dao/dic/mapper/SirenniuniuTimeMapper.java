package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.SirenniuniuTime;
import com.idealighter.game.dao.dic.po.SirenniuniuTimeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SirenniuniuTimeMapper {
  long countByExample(SirenniuniuTimeExample example);

  int deleteByExample(SirenniuniuTimeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(SirenniuniuTime record);

  int insertSelective(SirenniuniuTime record);

  List<SirenniuniuTime> selectByExample(SirenniuniuTimeExample example);

  SirenniuniuTime selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") SirenniuniuTime record,
      @Param("example") SirenniuniuTimeExample example);

  int updateByExample(@Param("record") SirenniuniuTime record,
      @Param("example") SirenniuniuTimeExample example);

  int updateByPrimaryKeySelective(SirenniuniuTime record);

  int updateByPrimaryKey(SirenniuniuTime record);
}
