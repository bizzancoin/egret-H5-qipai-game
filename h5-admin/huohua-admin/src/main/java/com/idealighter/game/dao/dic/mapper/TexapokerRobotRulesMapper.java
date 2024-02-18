package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.TexapokerRobotRules;
import com.idealighter.game.dao.dic.po.TexapokerRobotRulesExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TexapokerRobotRulesMapper {
  long countByExample(TexapokerRobotRulesExample example);

  int deleteByExample(TexapokerRobotRulesExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(TexapokerRobotRules record);

  int insertSelective(TexapokerRobotRules record);

  List<TexapokerRobotRules> selectByExample(TexapokerRobotRulesExample example);

  TexapokerRobotRules selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") TexapokerRobotRules record,
      @Param("example") TexapokerRobotRulesExample example);

  int updateByExample(@Param("record") TexapokerRobotRules record,
      @Param("example") TexapokerRobotRulesExample example);

  int updateByPrimaryKeySelective(TexapokerRobotRules record);

  int updateByPrimaryKey(TexapokerRobotRules record);
}
