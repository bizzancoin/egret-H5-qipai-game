package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.WeishuwuRoom;
import com.idealighter.game.dao.dic.po.WeishuwuRoomExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface WeishuwuRoomMapper {
  long countByExample(WeishuwuRoomExample example);

  int deleteByExample(WeishuwuRoomExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(WeishuwuRoom record);

  int insertSelective(WeishuwuRoom record);

  List<WeishuwuRoom> selectByExample(WeishuwuRoomExample example);

  WeishuwuRoom selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") WeishuwuRoom record,
      @Param("example") WeishuwuRoomExample example);

  int updateByExample(@Param("record") WeishuwuRoom record,
      @Param("example") WeishuwuRoomExample example);

  int updateByPrimaryKeySelective(WeishuwuRoom record);

  int updateByPrimaryKey(WeishuwuRoom record);
}
