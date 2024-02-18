package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.JcbyStrategy;
import com.idealighter.game.dao.dic.po.JcbyStrategyExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface JcbyStrategyMapper {
  long countByExample(JcbyStrategyExample example);

  int deleteByExample(JcbyStrategyExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(JcbyStrategy record);

  int insertSelective(JcbyStrategy record);

  List<JcbyStrategy> selectByExample(JcbyStrategyExample example);

  JcbyStrategy selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") JcbyStrategy record,
      @Param("example") JcbyStrategyExample example);

  int updateByExample(@Param("record") JcbyStrategy record,
      @Param("example") JcbyStrategyExample example);

  int updateByPrimaryKeySelective(JcbyStrategy record);

  int updateByPrimaryKey(JcbyStrategy record);
}
