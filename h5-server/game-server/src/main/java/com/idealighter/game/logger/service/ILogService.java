package com.idealighter.game.logger.service;

import com.google.inject.ImplementedBy;

import com.idealighter.game.logger.service.bo.GameGoldLogBo;
import com.idealighter.game.logger.service.bo.SafeGoldLogBo;

import java.util.List;

@ImplementedBy(value = LogService.class)
public interface ILogService {

  List<GameGoldLogBo> findGameGoldLogByLimit(Integer id, String channelId, String date, int limit);

  List<SafeGoldLogBo> findSafeGoldLogByLimit(Integer id, String channelId, String date, int limit);
}
