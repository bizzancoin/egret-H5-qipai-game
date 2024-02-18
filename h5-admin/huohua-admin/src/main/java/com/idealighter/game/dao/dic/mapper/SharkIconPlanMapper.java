package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.SharkIconPlan;
import com.idealighter.game.dao.dic.po.SharkIconPlanExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SharkIconPlanMapper {
  long countByExample(SharkIconPlanExample example);

  int deleteByExample(SharkIconPlanExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(SharkIconPlan record);

  int insertSelective(SharkIconPlan record);

  List<SharkIconPlan> selectByExample(SharkIconPlanExample example);

  SharkIconPlan selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") SharkIconPlan record,
      @Param("example") SharkIconPlanExample example);

  int updateByExample(@Param("record") SharkIconPlan record,
      @Param("example") SharkIconPlanExample example);

  int updateByPrimaryKeySelective(SharkIconPlan record);

  int updateByPrimaryKey(SharkIconPlan record);
}
