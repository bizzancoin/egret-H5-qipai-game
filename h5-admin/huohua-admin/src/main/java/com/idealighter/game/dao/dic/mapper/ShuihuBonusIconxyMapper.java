package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.ShuihuBonusIconxy;
import com.idealighter.game.dao.dic.po.ShuihuBonusIconxyExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ShuihuBonusIconxyMapper {
  long countByExample(ShuihuBonusIconxyExample example);

  int deleteByExample(ShuihuBonusIconxyExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(ShuihuBonusIconxy record);

  int insertSelective(ShuihuBonusIconxy record);

  List<ShuihuBonusIconxy> selectByExample(ShuihuBonusIconxyExample example);

  ShuihuBonusIconxy selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") ShuihuBonusIconxy record,
      @Param("example") ShuihuBonusIconxyExample example);

  int updateByExample(@Param("record") ShuihuBonusIconxy record,
      @Param("example") ShuihuBonusIconxyExample example);

  int updateByPrimaryKeySelective(ShuihuBonusIconxy record);

  int updateByPrimaryKey(ShuihuBonusIconxy record);
}
