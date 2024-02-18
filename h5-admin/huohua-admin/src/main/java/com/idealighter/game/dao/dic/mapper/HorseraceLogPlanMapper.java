package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.HorseraceLogPlan;
import com.idealighter.game.dao.dic.po.HorseraceLogPlanExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface HorseraceLogPlanMapper {
  long countByExample(HorseraceLogPlanExample example);

  int deleteByExample(HorseraceLogPlanExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(HorseraceLogPlan record);

  int insertSelective(HorseraceLogPlan record);

  List<HorseraceLogPlan> selectByExample(HorseraceLogPlanExample example);

  HorseraceLogPlan selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") HorseraceLogPlan record,
      @Param("example") HorseraceLogPlanExample example);

  int updateByExample(@Param("record") HorseraceLogPlan record,
      @Param("example") HorseraceLogPlanExample example);

  int updateByPrimaryKeySelective(HorseraceLogPlan record);

  int updateByPrimaryKey(HorseraceLogPlan record);
}
