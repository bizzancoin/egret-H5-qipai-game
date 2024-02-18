package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.WknhRoom;
import com.idealighter.game.dao.dic.po.WknhRoomExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface WknhRoomMapper {
  long countByExample(WknhRoomExample example);

  int deleteByExample(WknhRoomExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(WknhRoom record);

  int insertSelective(WknhRoom record);

  List<WknhRoom> selectByExample(WknhRoomExample example);

  WknhRoom selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") WknhRoom record,
      @Param("example") WknhRoomExample example);

  int updateByExample(@Param("record") WknhRoom record, @Param("example") WknhRoomExample example);

  int updateByPrimaryKeySelective(WknhRoom record);

  int updateByPrimaryKey(WknhRoom record);
}
