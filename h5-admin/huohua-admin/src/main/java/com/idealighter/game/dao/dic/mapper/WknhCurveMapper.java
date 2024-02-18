package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.WknhCurve;
import com.idealighter.game.dao.dic.po.WknhCurveExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface WknhCurveMapper {
  long countByExample(WknhCurveExample example);

  int deleteByExample(WknhCurveExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(WknhCurve record);

  int insertSelective(WknhCurve record);

  List<WknhCurve> selectByExample(WknhCurveExample example);

  WknhCurve selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") WknhCurve record,
      @Param("example") WknhCurveExample example);

  int updateByExample(@Param("record") WknhCurve record,
      @Param("example") WknhCurveExample example);

  int updateByPrimaryKeySelective(WknhCurve record);

  int updateByPrimaryKey(WknhCurve record);
}
