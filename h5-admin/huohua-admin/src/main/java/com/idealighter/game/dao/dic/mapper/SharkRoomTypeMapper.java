package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.SharkRoomType;
import com.idealighter.game.dao.dic.po.SharkRoomTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SharkRoomTypeMapper {
  long countByExample(SharkRoomTypeExample example);

  int deleteByExample(SharkRoomTypeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(SharkRoomType record);

  int insertSelective(SharkRoomType record);

  List<SharkRoomType> selectByExample(SharkRoomTypeExample example);

  SharkRoomType selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") SharkRoomType record,
      @Param("example") SharkRoomTypeExample example);

  int updateByExample(@Param("record") SharkRoomType record,
      @Param("example") SharkRoomTypeExample example);

  int updateByPrimaryKeySelective(SharkRoomType record);

  int updateByPrimaryKey(SharkRoomType record);
}
