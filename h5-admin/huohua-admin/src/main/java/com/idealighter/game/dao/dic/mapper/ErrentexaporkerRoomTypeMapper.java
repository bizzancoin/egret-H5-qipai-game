package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.ErrentexaporkerRoomType;
import com.idealighter.game.dao.dic.po.ErrentexaporkerRoomTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ErrentexaporkerRoomTypeMapper {
  long countByExample(ErrentexaporkerRoomTypeExample example);

  int deleteByExample(ErrentexaporkerRoomTypeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(ErrentexaporkerRoomType record);

  int insertSelective(ErrentexaporkerRoomType record);

  List<ErrentexaporkerRoomType> selectByExample(ErrentexaporkerRoomTypeExample example);

  ErrentexaporkerRoomType selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") ErrentexaporkerRoomType record,
      @Param("example") ErrentexaporkerRoomTypeExample example);

  int updateByExample(@Param("record") ErrentexaporkerRoomType record,
      @Param("example") ErrentexaporkerRoomTypeExample example);

  int updateByPrimaryKeySelective(ErrentexaporkerRoomType record);

  int updateByPrimaryKey(ErrentexaporkerRoomType record);
}
