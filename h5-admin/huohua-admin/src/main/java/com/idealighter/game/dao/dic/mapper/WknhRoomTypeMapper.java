package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.WknhRoomType;
import com.idealighter.game.dao.dic.po.WknhRoomTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface WknhRoomTypeMapper {
  long countByExample(WknhRoomTypeExample example);

  int deleteByExample(WknhRoomTypeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(WknhRoomType record);

  int insertSelective(WknhRoomType record);

  List<WknhRoomType> selectByExample(WknhRoomTypeExample example);

  WknhRoomType selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") WknhRoomType record,
      @Param("example") WknhRoomTypeExample example);

  int updateByExample(@Param("record") WknhRoomType record,
      @Param("example") WknhRoomTypeExample example);

  int updateByPrimaryKeySelective(WknhRoomType record);

  int updateByPrimaryKey(WknhRoomType record);
}
