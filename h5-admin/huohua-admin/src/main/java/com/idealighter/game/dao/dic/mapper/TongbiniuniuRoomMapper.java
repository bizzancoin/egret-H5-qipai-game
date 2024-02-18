package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.TongbiniuniuRoom;
import com.idealighter.game.dao.dic.po.TongbiniuniuRoomExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TongbiniuniuRoomMapper {
  long countByExample(TongbiniuniuRoomExample example);

  int deleteByExample(TongbiniuniuRoomExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(TongbiniuniuRoom record);

  int insertSelective(TongbiniuniuRoom record);

  List<TongbiniuniuRoom> selectByExample(TongbiniuniuRoomExample example);

  TongbiniuniuRoom selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") TongbiniuniuRoom record,
      @Param("example") TongbiniuniuRoomExample example);

  int updateByExample(@Param("record") TongbiniuniuRoom record,
      @Param("example") TongbiniuniuRoomExample example);

  int updateByPrimaryKeySelective(TongbiniuniuRoom record);

  int updateByPrimaryKey(TongbiniuniuRoom record);
}
