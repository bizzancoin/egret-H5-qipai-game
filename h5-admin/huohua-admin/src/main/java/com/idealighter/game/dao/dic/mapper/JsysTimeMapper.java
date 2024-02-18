package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.JsysTime;
import com.idealighter.game.dao.dic.po.JsysTimeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface JsysTimeMapper {
  long countByExample(JsysTimeExample example);

  int deleteByExample(JsysTimeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(JsysTime record);

  int insertSelective(JsysTime record);

  List<JsysTime> selectByExample(JsysTimeExample example);

  JsysTime selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") JsysTime record,
      @Param("example") JsysTimeExample example);

  int updateByExample(@Param("record") JsysTime record, @Param("example") JsysTimeExample example);

  int updateByPrimaryKeySelective(JsysTime record);

  int updateByPrimaryKey(JsysTime record);
}
