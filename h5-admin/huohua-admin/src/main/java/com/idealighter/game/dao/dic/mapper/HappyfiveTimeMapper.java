package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.HappyfiveTime;
import com.idealighter.game.dao.dic.po.HappyfiveTimeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface HappyfiveTimeMapper {
  long countByExample(HappyfiveTimeExample example);

  int deleteByExample(HappyfiveTimeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(HappyfiveTime record);

  int insertSelective(HappyfiveTime record);

  List<HappyfiveTime> selectByExample(HappyfiveTimeExample example);

  HappyfiveTime selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") HappyfiveTime record,
      @Param("example") HappyfiveTimeExample example);

  int updateByExample(@Param("record") HappyfiveTime record,
      @Param("example") HappyfiveTimeExample example);

  int updateByPrimaryKeySelective(HappyfiveTime record);

  int updateByPrimaryKey(HappyfiveTime record);
}
