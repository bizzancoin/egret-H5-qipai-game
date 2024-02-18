package com.idealighter.game.service.robotcfg.convert;

import com.idealighter.game.dao.dic.po.RobotConfig;
import com.idealighter.game.service.robotcfg.bo.RobotCfgListBo;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RobotCfgListBoConvert {
  @Mapping(target = "gameName", ignore = true)
  @Mapping(target = "roomName", ignore = true)
  RobotCfgListBo po2bo(RobotConfig po);

  List<RobotCfgListBo> po2bo(List<RobotConfig> pos);

  @Mapping(target = "bebankerchips", ignore = true)
  @Mapping(target = "bankerapplynums", ignore = true)
  @Mapping(target = "robotbankerapplynums", ignore = true)
  RobotConfig bo2po(RobotCfgListBo po);

  List<RobotConfig> bo2po(List<RobotCfgListBo> pos);
}
