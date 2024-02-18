package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.LkpyBattery;
import com.idealighter.game.dao.dic.po.LkpyBatteryExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface LkpyBatteryMapper {
  long countByExample(LkpyBatteryExample example);

  int deleteByExample(LkpyBatteryExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(LkpyBattery record);

  int insertSelective(LkpyBattery record);

  List<LkpyBattery> selectByExample(LkpyBatteryExample example);

  LkpyBattery selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") LkpyBattery record,
      @Param("example") LkpyBatteryExample example);

  int updateByExample(@Param("record") LkpyBattery record,
      @Param("example") LkpyBatteryExample example);

  int updateByPrimaryKeySelective(LkpyBattery record);

  int updateByPrimaryKey(LkpyBattery record);
}
