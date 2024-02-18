package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.WeishuwuRoomType;
import com.idealighter.game.dao.dic.po.WeishuwuRoomTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface WeishuwuRoomTypeMapper {
  long countByExample(WeishuwuRoomTypeExample example);

  int deleteByExample(WeishuwuRoomTypeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(WeishuwuRoomType record);

  int insertSelective(WeishuwuRoomType record);

  List<WeishuwuRoomType> selectByExample(WeishuwuRoomTypeExample example);

  WeishuwuRoomType selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") WeishuwuRoomType record,
      @Param("example") WeishuwuRoomTypeExample example);

  int updateByExample(@Param("record") WeishuwuRoomType record,
      @Param("example") WeishuwuRoomTypeExample example);

  int updateByPrimaryKeySelective(WeishuwuRoomType record);

  int updateByPrimaryKey(WeishuwuRoomType record);
}
