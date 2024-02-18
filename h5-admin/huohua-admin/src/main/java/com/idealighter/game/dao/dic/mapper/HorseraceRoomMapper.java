package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.HorseraceRoom;
import com.idealighter.game.dao.dic.po.HorseraceRoomExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface HorseraceRoomMapper {
  long countByExample(HorseraceRoomExample example);

  int deleteByExample(HorseraceRoomExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(HorseraceRoom record);

  int insertSelective(HorseraceRoom record);

  List<HorseraceRoom> selectByExample(HorseraceRoomExample example);

  HorseraceRoom selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") HorseraceRoom record,
      @Param("example") HorseraceRoomExample example);

  int updateByExample(@Param("record") HorseraceRoom record,
      @Param("example") HorseraceRoomExample example);

  int updateByPrimaryKeySelective(HorseraceRoom record);

  int updateByPrimaryKey(HorseraceRoom record);
}
