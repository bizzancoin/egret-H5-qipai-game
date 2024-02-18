package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.ErrenniuniuRoom;
import com.idealighter.game.dao.dic.po.ErrenniuniuRoomExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ErrenniuniuRoomMapper {
  long countByExample(ErrenniuniuRoomExample example);

  int deleteByExample(ErrenniuniuRoomExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(ErrenniuniuRoom record);

  int insertSelective(ErrenniuniuRoom record);

  List<ErrenniuniuRoom> selectByExample(ErrenniuniuRoomExample example);

  ErrenniuniuRoom selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") ErrenniuniuRoom record,
      @Param("example") ErrenniuniuRoomExample example);

  int updateByExample(@Param("record") ErrenniuniuRoom record,
      @Param("example") ErrenniuniuRoomExample example);

  int updateByPrimaryKeySelective(ErrenniuniuRoom record);

  int updateByPrimaryKey(ErrenniuniuRoom record);
}
