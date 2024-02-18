package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.MatchRoomType;
import com.idealighter.game.dao.dic.po.MatchRoomTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MatchRoomTypeMapper {
  long countByExample(MatchRoomTypeExample example);

  int deleteByExample(MatchRoomTypeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(MatchRoomType record);

  int insertSelective(MatchRoomType record);

  List<MatchRoomType> selectByExample(MatchRoomTypeExample example);

  MatchRoomType selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") MatchRoomType record,
      @Param("example") MatchRoomTypeExample example);

  int updateByExample(@Param("record") MatchRoomType record,
      @Param("example") MatchRoomTypeExample example);

  int updateByPrimaryKeySelective(MatchRoomType record);

  int updateByPrimaryKey(MatchRoomType record);
}
