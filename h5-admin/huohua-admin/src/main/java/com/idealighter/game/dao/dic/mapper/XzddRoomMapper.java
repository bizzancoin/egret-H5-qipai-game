package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.XzddRoom;
import com.idealighter.game.dao.dic.po.XzddRoomExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface XzddRoomMapper {
  long countByExample(XzddRoomExample example);

  int deleteByExample(XzddRoomExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(XzddRoom record);

  int insertSelective(XzddRoom record);

  List<XzddRoom> selectByExample(XzddRoomExample example);

  XzddRoom selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") XzddRoom record,
      @Param("example") XzddRoomExample example);

  int updateByExample(@Param("record") XzddRoom record, @Param("example") XzddRoomExample example);

  int updateByPrimaryKeySelective(XzddRoom record);

  int updateByPrimaryKey(XzddRoom record);
}
