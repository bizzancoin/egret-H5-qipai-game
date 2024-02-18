package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.LkpyStrategy;
import com.idealighter.game.dao.dic.po.LkpyStrategyExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface LkpyStrategyMapper {
  long countByExample(LkpyStrategyExample example);

  int deleteByExample(LkpyStrategyExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(LkpyStrategy record);

  int insertSelective(LkpyStrategy record);

  List<LkpyStrategy> selectByExample(LkpyStrategyExample example);

  LkpyStrategy selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") LkpyStrategy record,
      @Param("example") LkpyStrategyExample example);

  int updateByExample(@Param("record") LkpyStrategy record,
      @Param("example") LkpyStrategyExample example);

  int updateByPrimaryKeySelective(LkpyStrategy record);

  int updateByPrimaryKey(LkpyStrategy record);
}
