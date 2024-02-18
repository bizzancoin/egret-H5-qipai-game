package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.XzddRoomType;
import com.idealighter.game.dao.dic.po.XzddRoomTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface XzddRoomTypeMapper {
  long countByExample(XzddRoomTypeExample example);

  int deleteByExample(XzddRoomTypeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(XzddRoomType record);

  int insertSelective(XzddRoomType record);

  List<XzddRoomType> selectByExample(XzddRoomTypeExample example);

  XzddRoomType selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") XzddRoomType record,
      @Param("example") XzddRoomTypeExample example);

  int updateByExample(@Param("record") XzddRoomType record,
      @Param("example") XzddRoomTypeExample example);

  int updateByPrimaryKeySelective(XzddRoomType record);

  int updateByPrimaryKey(XzddRoomType record);
}
