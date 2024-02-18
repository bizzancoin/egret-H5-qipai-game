package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.HorseraceTime;
import com.idealighter.game.dao.dic.po.HorseraceTimeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface HorseraceTimeMapper {
  long countByExample(HorseraceTimeExample example);

  int deleteByExample(HorseraceTimeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(HorseraceTime record);

  int insertSelective(HorseraceTime record);

  List<HorseraceTime> selectByExample(HorseraceTimeExample example);

  HorseraceTime selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") HorseraceTime record,
      @Param("example") HorseraceTimeExample example);

  int updateByExample(@Param("record") HorseraceTime record,
      @Param("example") HorseraceTimeExample example);

  int updateByPrimaryKeySelective(HorseraceTime record);

  int updateByPrimaryKey(HorseraceTime record);
}
