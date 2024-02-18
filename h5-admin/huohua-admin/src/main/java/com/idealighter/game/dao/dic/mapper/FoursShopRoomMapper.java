package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.FoursShopRoom;
import com.idealighter.game.dao.dic.po.FoursShopRoomExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface FoursShopRoomMapper {
  long countByExample(FoursShopRoomExample example);

  int deleteByExample(FoursShopRoomExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(FoursShopRoom record);

  int insertSelective(FoursShopRoom record);

  List<FoursShopRoom> selectByExample(FoursShopRoomExample example);

  FoursShopRoom selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") FoursShopRoom record,
      @Param("example") FoursShopRoomExample example);

  int updateByExample(@Param("record") FoursShopRoom record,
      @Param("example") FoursShopRoomExample example);

  int updateByPrimaryKeySelective(FoursShopRoom record);

  int updateByPrimaryKey(FoursShopRoom record);
}
