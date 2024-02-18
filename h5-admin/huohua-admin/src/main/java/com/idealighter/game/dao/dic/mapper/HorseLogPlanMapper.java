package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.HorseLogPlan;
import com.idealighter.game.dao.dic.po.HorseLogPlanExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface HorseLogPlanMapper {
  long countByExample(HorseLogPlanExample example);

  int deleteByExample(HorseLogPlanExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(HorseLogPlan record);

  int insertSelective(HorseLogPlan record);

  List<HorseLogPlan> selectByExample(HorseLogPlanExample example);

  HorseLogPlan selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") HorseLogPlan record,
      @Param("example") HorseLogPlanExample example);

  int updateByExample(@Param("record") HorseLogPlan record,
      @Param("example") HorseLogPlanExample example);

  int updateByPrimaryKeySelective(HorseLogPlan record);

  int updateByPrimaryKey(HorseLogPlan record);
}
