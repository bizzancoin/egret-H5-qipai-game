package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.BairenniuniuRoomType;
import com.idealighter.game.dao.dic.po.BairenniuniuRoomTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BairenniuniuRoomTypeMapper {
  long countByExample(BairenniuniuRoomTypeExample example);

  int deleteByExample(BairenniuniuRoomTypeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(BairenniuniuRoomType record);

  int insertSelective(BairenniuniuRoomType record);

  List<BairenniuniuRoomType> selectByExample(BairenniuniuRoomTypeExample example);

  BairenniuniuRoomType selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") BairenniuniuRoomType record,
      @Param("example") BairenniuniuRoomTypeExample example);

  int updateByExample(@Param("record") BairenniuniuRoomType record,
      @Param("example") BairenniuniuRoomTypeExample example);

  int updateByPrimaryKeySelective(BairenniuniuRoomType record);

  int updateByPrimaryKey(BairenniuniuRoomType record);
}
