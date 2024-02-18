package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.HorseraceItem;
import com.idealighter.game.dao.dic.po.HorseraceItemExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface HorseraceItemMapper {
  long countByExample(HorseraceItemExample example);

  int deleteByExample(HorseraceItemExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(HorseraceItem record);

  int insertSelective(HorseraceItem record);

  List<HorseraceItem> selectByExample(HorseraceItemExample example);

  HorseraceItem selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") HorseraceItem record,
      @Param("example") HorseraceItemExample example);

  int updateByExample(@Param("record") HorseraceItem record,
      @Param("example") HorseraceItemExample example);

  int updateByPrimaryKeySelective(HorseraceItem record);

  int updateByPrimaryKey(HorseraceItem record);
}
