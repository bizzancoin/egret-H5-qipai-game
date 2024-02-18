package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.JcbyCurve;
import com.idealighter.game.dao.dic.po.JcbyCurveExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface JcbyCurveMapper {
  long countByExample(JcbyCurveExample example);

  int deleteByExample(JcbyCurveExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(JcbyCurve record);

  int insertSelective(JcbyCurve record);

  List<JcbyCurve> selectByExample(JcbyCurveExample example);

  JcbyCurve selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") JcbyCurve record,
      @Param("example") JcbyCurveExample example);

  int updateByExample(@Param("record") JcbyCurve record,
      @Param("example") JcbyCurveExample example);

  int updateByPrimaryKeySelective(JcbyCurve record);

  int updateByPrimaryKey(JcbyCurve record);
}
