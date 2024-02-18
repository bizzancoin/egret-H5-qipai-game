package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.FoursShopRoomType;
import com.idealighter.game.dao.dic.po.FoursShopRoomTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface FoursShopRoomTypeMapper {
  long countByExample(FoursShopRoomTypeExample example);

  int deleteByExample(FoursShopRoomTypeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(FoursShopRoomType record);

  int insertSelective(FoursShopRoomType record);

  List<FoursShopRoomType> selectByExample(FoursShopRoomTypeExample example);

  FoursShopRoomType selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") FoursShopRoomType record,
      @Param("example") FoursShopRoomTypeExample example);

  int updateByExample(@Param("record") FoursShopRoomType record,
      @Param("example") FoursShopRoomTypeExample example);

  int updateByPrimaryKeySelective(FoursShopRoomType record);

  int updateByPrimaryKey(FoursShopRoomType record);
}
