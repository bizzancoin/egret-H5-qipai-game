
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.SchedulerJobDomain;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface SchedulerJobMapper {

  @Select("select * from scheduler_job")
  List<SchedulerJobDomain> selectAll();
}
