package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.XzddMultiple;
import com.idealighter.game.dao.dic.po.XzddMultipleExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface XzddMultipleMapper {
  long countByExample(XzddMultipleExample example);

  int deleteByExample(XzddMultipleExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(XzddMultiple record);

  int insertSelective(XzddMultiple record);

  List<XzddMultiple> selectByExample(XzddMultipleExample example);

  XzddMultiple selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") XzddMultiple record,
      @Param("example") XzddMultipleExample example);

  int updateByExample(@Param("record") XzddMultiple record,
      @Param("example") XzddMultipleExample example);

  int updateByPrimaryKeySelective(XzddMultiple record);

  int updateByPrimaryKey(XzddMultiple record);
}
