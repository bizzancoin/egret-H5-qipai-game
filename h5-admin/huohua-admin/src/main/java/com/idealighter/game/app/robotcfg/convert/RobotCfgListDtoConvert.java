package com.idealighter.game.app.robotcfg.convert;

import com.idealighter.game.app.robotcfg.dto.RobotCfgListDto;
import com.idealighter.game.service.robotcfg.bo.RobotCfgListBo;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RobotCfgListDtoConvert {
  
  RobotCfgListDto bo2dto(RobotCfgListBo bo);

  List<RobotCfgListDto> bo2dto(List<RobotCfgListBo> bos);

  @Mapping(target = "occupytable", expression = "java((byte)1)")
  @Mapping(target = "gamewithplayer", expression = "java((byte)1)")
  RobotCfgListBo dto2bo(RobotCfgListDto dto);

  List<RobotCfgListBo> dto2bo(List<RobotCfgListDto> dtos);
}
