package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.SirenniuniuRoomType;
import com.idealighter.game.dao.dic.po.SirenniuniuRoomTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SirenniuniuRoomTypeMapper {
  long countByExample(SirenniuniuRoomTypeExample example);

  int deleteByExample(SirenniuniuRoomTypeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(SirenniuniuRoomType record);

  int insertSelective(SirenniuniuRoomType record);

  List<SirenniuniuRoomType> selectByExample(SirenniuniuRoomTypeExample example);

  SirenniuniuRoomType selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") SirenniuniuRoomType record,
      @Param("example") SirenniuniuRoomTypeExample example);

  int updateByExample(@Param("record") SirenniuniuRoomType record,
      @Param("example") SirenniuniuRoomTypeExample example);

  int updateByPrimaryKeySelective(SirenniuniuRoomType record);

  int updateByPrimaryKey(SirenniuniuRoomType record);
}
