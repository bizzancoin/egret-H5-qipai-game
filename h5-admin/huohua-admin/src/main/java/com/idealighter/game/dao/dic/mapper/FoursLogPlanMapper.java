package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.FoursLogPlan;
import com.idealighter.game.dao.dic.po.FoursLogPlanExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface FoursLogPlanMapper {
  long countByExample(FoursLogPlanExample example);

  int deleteByExample(FoursLogPlanExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(FoursLogPlan record);

  int insertSelective(FoursLogPlan record);

  List<FoursLogPlan> selectByExample(FoursLogPlanExample example);

  FoursLogPlan selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") FoursLogPlan record,
      @Param("example") FoursLogPlanExample example);

  int updateByExample(@Param("record") FoursLogPlan record,
      @Param("example") FoursLogPlanExample example);

  int updateByPrimaryKeySelective(FoursLogPlan record);

  int updateByPrimaryKey(FoursLogPlan record);
}
