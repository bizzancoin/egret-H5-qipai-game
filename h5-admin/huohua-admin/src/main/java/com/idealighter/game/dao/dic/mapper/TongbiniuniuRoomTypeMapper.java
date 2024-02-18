package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.TongbiniuniuRoomType;
import com.idealighter.game.dao.dic.po.TongbiniuniuRoomTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TongbiniuniuRoomTypeMapper {
  long countByExample(TongbiniuniuRoomTypeExample example);

  int deleteByExample(TongbiniuniuRoomTypeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(TongbiniuniuRoomType record);

  int insertSelective(TongbiniuniuRoomType record);

  List<TongbiniuniuRoomType> selectByExample(TongbiniuniuRoomTypeExample example);

  TongbiniuniuRoomType selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") TongbiniuniuRoomType record,
      @Param("example") TongbiniuniuRoomTypeExample example);

  int updateByExample(@Param("record") TongbiniuniuRoomType record,
      @Param("example") TongbiniuniuRoomTypeExample example);

  int updateByPrimaryKeySelective(TongbiniuniuRoomType record);

  int updateByPrimaryKey(TongbiniuniuRoomType record);
}
