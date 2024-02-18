package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.HorseraceSence1;
import com.idealighter.game.dao.dic.po.HorseraceSence1Example;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface HorseraceSence1Mapper {
  long countByExample(HorseraceSence1Example example);

  int deleteByExample(HorseraceSence1Example example);

  int deleteByPrimaryKey(Integer id);

  int insert(HorseraceSence1 record);

  int insertSelective(HorseraceSence1 record);

  List<HorseraceSence1> selectByExample(HorseraceSence1Example example);

  HorseraceSence1 selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") HorseraceSence1 record,
      @Param("example") HorseraceSence1Example example);

  int updateByExample(@Param("record") HorseraceSence1 record,
      @Param("example") HorseraceSence1Example example);

  int updateByPrimaryKeySelective(HorseraceSence1 record);

  int updateByPrimaryKey(HorseraceSence1 record);
}
