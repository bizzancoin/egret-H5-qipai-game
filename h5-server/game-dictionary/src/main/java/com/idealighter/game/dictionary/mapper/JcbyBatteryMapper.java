
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.JcbyBatteryDomain;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface JcbyBatteryMapper {

  @Select("select * from jcby_battery")
  List<JcbyBatteryDomain> selectAll();
}
