package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.TexaporkerRoomType;
import com.idealighter.game.dao.dic.po.TexaporkerRoomTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TexaporkerRoomTypeMapper {
  long countByExample(TexaporkerRoomTypeExample example);

  int deleteByExample(TexaporkerRoomTypeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(TexaporkerRoomType record);

  int insertSelective(TexaporkerRoomType record);

  List<TexaporkerRoomType> selectByExample(TexaporkerRoomTypeExample example);

  TexaporkerRoomType selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") TexaporkerRoomType record,
      @Param("example") TexaporkerRoomTypeExample example);

  int updateByExample(@Param("record") TexaporkerRoomType record,
      @Param("example") TexaporkerRoomTypeExample example);

  int updateByPrimaryKeySelective(TexaporkerRoomType record);

  int updateByPrimaryKey(TexaporkerRoomType record);
}
