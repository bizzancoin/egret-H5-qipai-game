package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.HorseraceRoomType;
import com.idealighter.game.dao.dic.po.HorseraceRoomTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface HorseraceRoomTypeMapper {
  long countByExample(HorseraceRoomTypeExample example);

  int deleteByExample(HorseraceRoomTypeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(HorseraceRoomType record);

  int insertSelective(HorseraceRoomType record);

  List<HorseraceRoomType> selectByExample(HorseraceRoomTypeExample example);

  HorseraceRoomType selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") HorseraceRoomType record,
      @Param("example") HorseraceRoomTypeExample example);

  int updateByExample(@Param("record") HorseraceRoomType record,
      @Param("example") HorseraceRoomTypeExample example);

  int updateByPrimaryKeySelective(HorseraceRoomType record);

  int updateByPrimaryKey(HorseraceRoomType record);
}
