package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.HorseraceSenceType;
import com.idealighter.game.dao.dic.po.HorseraceSenceTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface HorseraceSenceTypeMapper {
  long countByExample(HorseraceSenceTypeExample example);

  int deleteByExample(HorseraceSenceTypeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(HorseraceSenceType record);

  int insertSelective(HorseraceSenceType record);

  List<HorseraceSenceType> selectByExample(HorseraceSenceTypeExample example);

  HorseraceSenceType selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") HorseraceSenceType record,
      @Param("example") HorseraceSenceTypeExample example);

  int updateByExample(@Param("record") HorseraceSenceType record,
      @Param("example") HorseraceSenceTypeExample example);

  int updateByPrimaryKeySelective(HorseraceSenceType record);

  int updateByPrimaryKey(HorseraceSenceType record);
}
