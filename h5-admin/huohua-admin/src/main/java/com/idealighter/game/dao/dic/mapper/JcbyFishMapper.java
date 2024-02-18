package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.JcbyFish;
import com.idealighter.game.dao.dic.po.JcbyFishExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface JcbyFishMapper {
  long countByExample(JcbyFishExample example);

  int deleteByExample(JcbyFishExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(JcbyFish record);

  int insertSelective(JcbyFish record);

  List<JcbyFish> selectByExample(JcbyFishExample example);

  JcbyFish selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") JcbyFish record,
      @Param("example") JcbyFishExample example);

  int updateByExample(@Param("record") JcbyFish record, @Param("example") JcbyFishExample example);

  int updateByPrimaryKeySelective(JcbyFish record);

  int updateByPrimaryKey(JcbyFish record);
}
