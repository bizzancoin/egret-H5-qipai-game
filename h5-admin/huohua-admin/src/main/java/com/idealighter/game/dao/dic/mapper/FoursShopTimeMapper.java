package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.FoursShopTime;
import com.idealighter.game.dao.dic.po.FoursShopTimeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface FoursShopTimeMapper {
  long countByExample(FoursShopTimeExample example);

  int deleteByExample(FoursShopTimeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(FoursShopTime record);

  int insertSelective(FoursShopTime record);

  List<FoursShopTime> selectByExample(FoursShopTimeExample example);

  FoursShopTime selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") FoursShopTime record,
      @Param("example") FoursShopTimeExample example);

  int updateByExample(@Param("record") FoursShopTime record,
      @Param("example") FoursShopTimeExample example);

  int updateByPrimaryKeySelective(FoursShopTime record);

  int updateByPrimaryKey(FoursShopTime record);
}
