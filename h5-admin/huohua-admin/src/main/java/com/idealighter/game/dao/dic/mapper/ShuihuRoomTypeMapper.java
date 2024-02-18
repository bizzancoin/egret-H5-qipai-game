package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.ShuihuRoomType;
import com.idealighter.game.dao.dic.po.ShuihuRoomTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ShuihuRoomTypeMapper {
  long countByExample(ShuihuRoomTypeExample example);

  int deleteByExample(ShuihuRoomTypeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(ShuihuRoomType record);

  int insertSelective(ShuihuRoomType record);

  List<ShuihuRoomType> selectByExample(ShuihuRoomTypeExample example);

  ShuihuRoomType selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") ShuihuRoomType record,
      @Param("example") ShuihuRoomTypeExample example);

  int updateByExample(@Param("record") ShuihuRoomType record,
      @Param("example") ShuihuRoomTypeExample example);

  int updateByPrimaryKeySelective(ShuihuRoomType record);

  int updateByPrimaryKey(ShuihuRoomType record);
}
