package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.JoyfiveEffect;
import com.idealighter.game.dao.dic.po.JoyfiveEffectExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface JoyfiveEffectMapper {
  long countByExample(JoyfiveEffectExample example);

  int deleteByExample(JoyfiveEffectExample example);

  int deleteByPrimaryKey(String key);

  int insert(JoyfiveEffect record);

  int insertSelective(JoyfiveEffect record);

  List<JoyfiveEffect> selectByExample(JoyfiveEffectExample example);

  JoyfiveEffect selectByPrimaryKey(String key);

  int updateByExampleSelective(@Param("record") JoyfiveEffect record,
      @Param("example") JoyfiveEffectExample example);

  int updateByExample(@Param("record") JoyfiveEffect record,
      @Param("example") JoyfiveEffectExample example);

  int updateByPrimaryKeySelective(JoyfiveEffect record);

  int updateByPrimaryKey(JoyfiveEffect record);
}
