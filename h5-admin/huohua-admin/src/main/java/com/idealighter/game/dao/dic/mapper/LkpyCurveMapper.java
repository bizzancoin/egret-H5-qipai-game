package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.LkpyCurve;
import com.idealighter.game.dao.dic.po.LkpyCurveExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface LkpyCurveMapper {
  long countByExample(LkpyCurveExample example);

  int deleteByExample(LkpyCurveExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(LkpyCurve record);

  int insertSelective(LkpyCurve record);

  List<LkpyCurve> selectByExample(LkpyCurveExample example);

  LkpyCurve selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") LkpyCurve record,
      @Param("example") LkpyCurveExample example);

  int updateByExample(@Param("record") LkpyCurve record,
      @Param("example") LkpyCurveExample example);

  int updateByPrimaryKeySelective(LkpyCurve record);

  int updateByPrimaryKey(LkpyCurve record);
}
