package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.ErrenniuniuRoomType;
import com.idealighter.game.dao.dic.po.ErrenniuniuRoomTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ErrenniuniuRoomTypeMapper {
  long countByExample(ErrenniuniuRoomTypeExample example);

  int deleteByExample(ErrenniuniuRoomTypeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(ErrenniuniuRoomType record);

  int insertSelective(ErrenniuniuRoomType record);

  List<ErrenniuniuRoomType> selectByExample(ErrenniuniuRoomTypeExample example);

  ErrenniuniuRoomType selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") ErrenniuniuRoomType record,
      @Param("example") ErrenniuniuRoomTypeExample example);

  int updateByExample(@Param("record") ErrenniuniuRoomType record,
      @Param("example") ErrenniuniuRoomTypeExample example);

  int updateByPrimaryKeySelective(ErrenniuniuRoomType record);

  int updateByPrimaryKey(ErrenniuniuRoomType record);
}
