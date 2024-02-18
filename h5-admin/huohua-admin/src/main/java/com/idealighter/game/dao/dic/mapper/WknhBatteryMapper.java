package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.WknhBattery;
import com.idealighter.game.dao.dic.po.WknhBatteryExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface WknhBatteryMapper {
  long countByExample(WknhBatteryExample example);

  int deleteByExample(WknhBatteryExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(WknhBattery record);

  int insertSelective(WknhBattery record);

  List<WknhBattery> selectByExample(WknhBatteryExample example);

  WknhBattery selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") WknhBattery record,
      @Param("example") WknhBatteryExample example);

  int updateByExample(@Param("record") WknhBattery record,
      @Param("example") WknhBatteryExample example);

  int updateByPrimaryKeySelective(WknhBattery record);

  int updateByPrimaryKey(WknhBattery record);
}
