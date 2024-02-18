package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.XzddTime;
import com.idealighter.game.dao.dic.po.XzddTimeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface XzddTimeMapper {
  long countByExample(XzddTimeExample example);

  int deleteByExample(XzddTimeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(XzddTime record);

  int insertSelective(XzddTime record);

  List<XzddTime> selectByExample(XzddTimeExample example);

  XzddTime selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") XzddTime record,
      @Param("example") XzddTimeExample example);

  int updateByExample(@Param("record") XzddTime record, @Param("example") XzddTimeExample example);

  int updateByPrimaryKeySelective(XzddTime record);

  int updateByPrimaryKey(XzddTime record);
}
