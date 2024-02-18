package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.ShuihuBonusIcon;
import com.idealighter.game.dao.dic.po.ShuihuBonusIconExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ShuihuBonusIconMapper {
  long countByExample(ShuihuBonusIconExample example);

  int deleteByExample(ShuihuBonusIconExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(ShuihuBonusIcon record);

  int insertSelective(ShuihuBonusIcon record);

  List<ShuihuBonusIcon> selectByExample(ShuihuBonusIconExample example);

  ShuihuBonusIcon selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") ShuihuBonusIcon record,
      @Param("example") ShuihuBonusIconExample example);

  int updateByExample(@Param("record") ShuihuBonusIcon record,
      @Param("example") ShuihuBonusIconExample example);

  int updateByPrimaryKeySelective(ShuihuBonusIcon record);

  int updateByPrimaryKey(ShuihuBonusIcon record);
}
