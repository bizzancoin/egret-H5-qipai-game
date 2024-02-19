package com.idealighter.game.admin.roomctl.convert;

import com.idealighter.game.admin.roomctl.dto.StrategyDto;
import com.idealighter.game.core.service.prizepool.struct.room.ControlStrategy;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StrategyDtoConvert {
  StrategyDtoConvert INSTANCE = Mappers.getMapper(StrategyDtoConvert.class);

  public ControlStrategy dto2ControlStrategy(StrategyDto dto);

  public StrategyDto dto2ControlStrategy(ControlStrategy strategy);
}
