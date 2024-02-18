package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.RobotConfig;
import com.idealighter.game.dao.dic.po.RobotConfigExample;
import com.idealighter.game.service.robotcfg.bo.RobotLeftBo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface RobotConfigMapper {
  long countByExample(RobotConfigExample example);

  int deleteByExample(RobotConfigExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(RobotConfig record);

  int insertSelective(RobotConfig record);

  List<RobotConfig> selectByExample(RobotConfigExample example);

  RobotConfig selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") RobotConfig record,
      @Param("example") RobotConfigExample example);

  int updateByExample(@Param("record") RobotConfig record,
      @Param("example") RobotConfigExample example);

  int updateByPrimaryKeySelective(RobotConfig record);

  int updateByPrimaryKey(RobotConfig record);


  @Select("select (#{total} - sum(time1Players)) as time1," //
      + "(#{total} - sum(time2Players)) as time2," //
      + "(#{total} - sum(time3Players)) as time3," //
      + "(#{total} - sum(time4Players)) as time4," //
      + "(#{total} - sum(time5Players)) as time5," //
      + "(#{total} - sum(time6Players)) as time6," //
      + "(#{total} - sum(time7Players)) as time7," //
      + "(#{total} - sum(time8Players)) as time8," //
      + "(#{total} - sum(time9Players)) as time9," //
      + "(#{total} - sum(time10Players)) as time10," //
      + "(#{total} - sum(time11Players)) as time11," //
      + "(#{total} - sum(time12Players)) as time12 " //
      + "from robot_config")
  public RobotLeftBo selectLeftRobot(int total);

}
