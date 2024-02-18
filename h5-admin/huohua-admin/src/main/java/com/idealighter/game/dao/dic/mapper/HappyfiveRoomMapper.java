package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.HappyfiveRoom;
import com.idealighter.game.dao.dic.po.HappyfiveRoomExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface HappyfiveRoomMapper {
  long countByExample(HappyfiveRoomExample example);

  int deleteByExample(HappyfiveRoomExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(HappyfiveRoom record);

  int insertSelective(HappyfiveRoom record);

  List<HappyfiveRoom> selectByExample(HappyfiveRoomExample example);

  HappyfiveRoom selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") HappyfiveRoom record,
      @Param("example") HappyfiveRoomExample example);

  int updateByExample(@Param("record") HappyfiveRoom record,
      @Param("example") HappyfiveRoomExample example);

  int updateByPrimaryKeySelective(HappyfiveRoom record);

  int updateByPrimaryKey(HappyfiveRoom record);
}
