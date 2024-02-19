package com.idealighter.game.logger.service.convert;

import com.idealighter.game.logger.dao.po.GameGoldLogPo;
import com.idealighter.game.logger.dao.po.SafeGoldLogPo;
import com.idealighter.game.logger.service.bo.GameGoldLogBo;
import com.idealighter.game.logger.service.bo.SafeGoldLogBo;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LogBoConvert {

  LogBoConvert INSTANCE = Mappers.getMapper(LogBoConvert.class);

  GameGoldLogBo toGameGoldLogBo(GameGoldLogPo gameGoldLogPo);

  List<GameGoldLogBo> toGameGoldLogBos(List<GameGoldLogPo> gameGoldLogPos);

  SafeGoldLogBo toSafeGoldLogBo(SafeGoldLogPo safeGoldLogPo);

  List<SafeGoldLogBo> toSafeGoldLogBos(List<SafeGoldLogPo> safeGoldLogPo);
}
