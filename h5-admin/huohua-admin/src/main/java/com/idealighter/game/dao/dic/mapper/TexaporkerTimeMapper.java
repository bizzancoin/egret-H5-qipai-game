package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.TexaporkerTime;
import com.idealighter.game.dao.dic.po.TexaporkerTimeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TexaporkerTimeMapper {
  long countByExample(TexaporkerTimeExample example);

  int deleteByExample(TexaporkerTimeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(TexaporkerTime record);

  int insertSelective(TexaporkerTime record);

  List<TexaporkerTime> selectByExample(TexaporkerTimeExample example);

  TexaporkerTime selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") TexaporkerTime record,
      @Param("example") TexaporkerTimeExample example);

  int updateByExample(@Param("record") TexaporkerTime record,
      @Param("example") TexaporkerTimeExample example);

  int updateByPrimaryKeySelective(TexaporkerTime record);

  int updateByPrimaryKey(TexaporkerTime record);
}
