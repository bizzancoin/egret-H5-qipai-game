package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.GameType;
import com.idealighter.game.dao.dic.po.GameTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface GameTypeMapper {
  long countByExample(GameTypeExample example);

  int deleteByExample(GameTypeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(GameType record);

  int insertSelective(GameType record);

  List<GameType> selectByExample(GameTypeExample example);

  GameType selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") GameType record,
      @Param("example") GameTypeExample example);

  int updateByExample(@Param("record") GameType record, @Param("example") GameTypeExample example);

  int updateByPrimaryKeySelective(GameType record);

  int updateByPrimaryKey(GameType record);
}
