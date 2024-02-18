package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.JcbyRoomType;
import com.idealighter.game.dao.dic.po.JcbyRoomTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface JcbyRoomTypeMapper {
  long countByExample(JcbyRoomTypeExample example);

  int deleteByExample(JcbyRoomTypeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(JcbyRoomType record);

  int insertSelective(JcbyRoomType record);

  List<JcbyRoomType> selectByExample(JcbyRoomTypeExample example);

  JcbyRoomType selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") JcbyRoomType record,
      @Param("example") JcbyRoomTypeExample example);

  int updateByExample(@Param("record") JcbyRoomType record,
      @Param("example") JcbyRoomTypeExample example);

  int updateByPrimaryKeySelective(JcbyRoomType record);

  int updateByPrimaryKey(JcbyRoomType record);
}
