package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.ErrenniuniuTime;
import com.idealighter.game.dao.dic.po.ErrenniuniuTimeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ErrenniuniuTimeMapper {
  long countByExample(ErrenniuniuTimeExample example);

  int deleteByExample(ErrenniuniuTimeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(ErrenniuniuTime record);

  int insertSelective(ErrenniuniuTime record);

  List<ErrenniuniuTime> selectByExample(ErrenniuniuTimeExample example);

  ErrenniuniuTime selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") ErrenniuniuTime record,
      @Param("example") ErrenniuniuTimeExample example);

  int updateByExample(@Param("record") ErrenniuniuTime record,
      @Param("example") ErrenniuniuTimeExample example);

  int updateByPrimaryKeySelective(ErrenniuniuTime record);

  int updateByPrimaryKey(ErrenniuniuTime record);
}
