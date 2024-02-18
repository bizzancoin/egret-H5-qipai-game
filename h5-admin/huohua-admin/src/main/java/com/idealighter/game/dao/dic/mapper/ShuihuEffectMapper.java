package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.ShuihuEffect;
import com.idealighter.game.dao.dic.po.ShuihuEffectExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ShuihuEffectMapper {
  long countByExample(ShuihuEffectExample example);

  int deleteByExample(ShuihuEffectExample example);

  int deleteByPrimaryKey(String key);

  int insert(ShuihuEffect record);

  int insertSelective(ShuihuEffect record);

  List<ShuihuEffect> selectByExample(ShuihuEffectExample example);

  ShuihuEffect selectByPrimaryKey(String key);

  int updateByExampleSelective(@Param("record") ShuihuEffect record,
      @Param("example") ShuihuEffectExample example);

  int updateByExample(@Param("record") ShuihuEffect record,
      @Param("example") ShuihuEffectExample example);

  int updateByPrimaryKeySelective(ShuihuEffect record);

  int updateByPrimaryKey(ShuihuEffect record);
}
