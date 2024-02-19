
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.RobotConfigDomain;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface RobotConfigMapper {

  @Select("select * from robot_config")
  List<RobotConfigDomain> selectAll();
}
