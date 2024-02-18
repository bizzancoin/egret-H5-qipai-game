package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.HappyfiveRoomType;
import com.idealighter.game.dao.dic.po.HappyfiveRoomTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface HappyfiveRoomTypeMapper {
  long countByExample(HappyfiveRoomTypeExample example);

  int deleteByExample(HappyfiveRoomTypeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(HappyfiveRoomType record);

  int insertSelective(HappyfiveRoomType record);

  List<HappyfiveRoomType> selectByExample(HappyfiveRoomTypeExample example);

  HappyfiveRoomType selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") HappyfiveRoomType record,
      @Param("example") HappyfiveRoomTypeExample example);

  int updateByExample(@Param("record") HappyfiveRoomType record,
      @Param("example") HappyfiveRoomTypeExample example);

  int updateByPrimaryKeySelective(HappyfiveRoomType record);

  int updateByPrimaryKey(HappyfiveRoomType record);
}
