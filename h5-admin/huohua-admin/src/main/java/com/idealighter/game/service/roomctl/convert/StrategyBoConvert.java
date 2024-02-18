package com.idealighter.game.service.roomctl.convert;

import com.idealighter.game.app.roomctl.dto.StrategyDto;
import com.idealighter.game.service.roomctl.bo.StrategyBo;
import org.mapstruct.Mapper;

@Mapper
public interface StrategyBoConvert {
  public StrategyBo dto2bo(StrategyDto dto);
}
