package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.TexaporkerRoom;
import com.idealighter.game.dao.dic.po.TexaporkerRoomExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TexaporkerRoomMapper {
  long countByExample(TexaporkerRoomExample example);

  int deleteByExample(TexaporkerRoomExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(TexaporkerRoom record);

  int insertSelective(TexaporkerRoom record);

  List<TexaporkerRoom> selectByExample(TexaporkerRoomExample example);

  TexaporkerRoom selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") TexaporkerRoom record,
      @Param("example") TexaporkerRoomExample example);

  int updateByExample(@Param("record") TexaporkerRoom record,
      @Param("example") TexaporkerRoomExample example);

  int updateByPrimaryKeySelective(TexaporkerRoom record);

  int updateByPrimaryKey(TexaporkerRoom record);
}
