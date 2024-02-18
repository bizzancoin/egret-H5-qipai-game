package com.idealighter.game.app.robotcfg.convert;

import com.idealighter.game.app.robotcfg.dto.RobotLeftDto;
import com.idealighter.game.app.robotcfg.dto.RoomConfigDto;
import com.idealighter.game.service.robotcfg.bo.RobotLeftBo;
import com.idealighter.game.service.robotcfg.bo.RoomConfigBo;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper
public interface RobotLeftDtoConvert {
  public RobotLeftDto bo2dto(RobotLeftBo bo);

  List<RoomConfigDto> toRoomConfigDtos(List<RoomConfigBo> roomConfigBos);
}
