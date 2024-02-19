package com.idealighter.game.logger.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.logger.dao.mapper.GameGoldLogMapper;
import com.idealighter.game.logger.dao.mapper.SafeGoldLogMapper;
import com.idealighter.game.logger.dao.po.GameGoldLogPo;
import com.idealighter.game.logger.dao.po.SafeGoldLogPo;
import com.idealighter.game.logger.service.bo.GameGoldLogBo;
import com.idealighter.game.logger.service.bo.SafeGoldLogBo;
import com.idealighter.game.logger.service.convert.LogBoConvert;

import java.util.List;

@Singleton
public class LogService implements ILogService {

  @Inject
  private GameGoldLogMapper gameGoldLogMapper;

  @Inject
  private SafeGoldLogMapper safeGoldLogMapper;

  @Override
  public List<GameGoldLogBo> findGameGoldLogByLimit(Integer id, String channelId, String date,
      int limit) {
    List<GameGoldLogPo> gameGoldLogPos = null;
    if (gameGoldLogMapper.existTable(date) > 0) {
      gameGoldLogPos = gameGoldLogMapper.selectByLimit(id, channelId, date, limit);
    }

    return LogBoConvert.INSTANCE.toGameGoldLogBos(gameGoldLogPos);
  }

  @Override
  public List<SafeGoldLogBo> findSafeGoldLogByLimit(Integer id, String channelId, String date,
      int limit) {
    List<SafeGoldLogPo> safeGoldLogPos = null;

    if (safeGoldLogMapper.existTable(date) > 0) {
      safeGoldLogPos = safeGoldLogMapper.selectByLimit(id, channelId, date, limit);
    }

    return LogBoConvert.INSTANCE.toSafeGoldLogBos(safeGoldLogPos);
  }

}
