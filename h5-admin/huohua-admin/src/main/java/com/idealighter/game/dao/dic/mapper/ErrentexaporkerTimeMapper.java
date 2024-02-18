package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.ErrentexaporkerTime;
import com.idealighter.game.dao.dic.po.ErrentexaporkerTimeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ErrentexaporkerTimeMapper {
  long countByExample(ErrentexaporkerTimeExample example);

  int deleteByExample(ErrentexaporkerTimeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(ErrentexaporkerTime record);

  int insertSelective(ErrentexaporkerTime record);

  List<ErrentexaporkerTime> selectByExample(ErrentexaporkerTimeExample example);

  ErrentexaporkerTime selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") ErrentexaporkerTime record,
      @Param("example") ErrentexaporkerTimeExample example);

  int updateByExample(@Param("record") ErrentexaporkerTime record,
      @Param("example") ErrentexaporkerTimeExample example);

  int updateByPrimaryKeySelective(ErrentexaporkerTime record);

  int updateByPrimaryKey(ErrentexaporkerTime record);
}
