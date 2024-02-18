package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.JcbyBattery;
import com.idealighter.game.dao.dic.po.JcbyBatteryExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface JcbyBatteryMapper {
  long countByExample(JcbyBatteryExample example);

  int deleteByExample(JcbyBatteryExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(JcbyBattery record);

  int insertSelective(JcbyBattery record);

  List<JcbyBattery> selectByExample(JcbyBatteryExample example);

  JcbyBattery selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") JcbyBattery record,
      @Param("example") JcbyBatteryExample example);

  int updateByExample(@Param("record") JcbyBattery record,
      @Param("example") JcbyBatteryExample example);

  int updateByPrimaryKeySelective(JcbyBattery record);

  int updateByPrimaryKey(JcbyBattery record);
}
