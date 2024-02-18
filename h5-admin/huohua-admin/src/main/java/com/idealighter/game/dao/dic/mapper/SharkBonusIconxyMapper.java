package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.SharkBonusIconxy;
import com.idealighter.game.dao.dic.po.SharkBonusIconxyExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SharkBonusIconxyMapper {
  long countByExample(SharkBonusIconxyExample example);

  int deleteByExample(SharkBonusIconxyExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(SharkBonusIconxy record);

  int insertSelective(SharkBonusIconxy record);

  List<SharkBonusIconxy> selectByExample(SharkBonusIconxyExample example);

  SharkBonusIconxy selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") SharkBonusIconxy record,
      @Param("example") SharkBonusIconxyExample example);

  int updateByExample(@Param("record") SharkBonusIconxy record,
      @Param("example") SharkBonusIconxyExample example);

  int updateByPrimaryKeySelective(SharkBonusIconxy record);

  int updateByPrimaryKey(SharkBonusIconxy record);
}
