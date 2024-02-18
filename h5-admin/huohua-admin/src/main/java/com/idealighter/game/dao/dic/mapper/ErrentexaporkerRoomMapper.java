package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.ErrentexaporkerRoom;
import com.idealighter.game.dao.dic.po.ErrentexaporkerRoomExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ErrentexaporkerRoomMapper {
  long countByExample(ErrentexaporkerRoomExample example);

  int deleteByExample(ErrentexaporkerRoomExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(ErrentexaporkerRoom record);

  int insertSelective(ErrentexaporkerRoom record);

  List<ErrentexaporkerRoom> selectByExample(ErrentexaporkerRoomExample example);

  ErrentexaporkerRoom selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") ErrentexaporkerRoom record,
      @Param("example") ErrentexaporkerRoomExample example);

  int updateByExample(@Param("record") ErrentexaporkerRoom record,
      @Param("example") ErrentexaporkerRoomExample example);

  int updateByPrimaryKeySelective(ErrentexaporkerRoom record);

  int updateByPrimaryKey(ErrentexaporkerRoom record);
}
