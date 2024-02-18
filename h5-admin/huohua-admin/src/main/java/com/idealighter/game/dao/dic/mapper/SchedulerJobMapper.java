package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.SchedulerJob;
import com.idealighter.game.dao.dic.po.SchedulerJobExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SchedulerJobMapper {
  long countByExample(SchedulerJobExample example);

  int deleteByExample(SchedulerJobExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(SchedulerJob record);

  int insertSelective(SchedulerJob record);

  List<SchedulerJob> selectByExample(SchedulerJobExample example);

  SchedulerJob selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") SchedulerJob record,
      @Param("example") SchedulerJobExample example);

  int updateByExample(@Param("record") SchedulerJob record,
      @Param("example") SchedulerJobExample example);

  int updateByPrimaryKeySelective(SchedulerJob record);

  int updateByPrimaryKey(SchedulerJob record);
}
