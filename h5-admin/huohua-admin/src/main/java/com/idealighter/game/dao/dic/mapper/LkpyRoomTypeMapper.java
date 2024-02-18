package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.LkpyRoomType;
import com.idealighter.game.dao.dic.po.LkpyRoomTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface LkpyRoomTypeMapper {
  long countByExample(LkpyRoomTypeExample example);

  int deleteByExample(LkpyRoomTypeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(LkpyRoomType record);

  int insertSelective(LkpyRoomType record);

  List<LkpyRoomType> selectByExample(LkpyRoomTypeExample example);

  LkpyRoomType selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") LkpyRoomType record,
      @Param("example") LkpyRoomTypeExample example);

  int updateByExample(@Param("record") LkpyRoomType record,
      @Param("example") LkpyRoomTypeExample example);

  int updateByPrimaryKeySelective(LkpyRoomType record);

  int updateByPrimaryKey(LkpyRoomType record);
}
