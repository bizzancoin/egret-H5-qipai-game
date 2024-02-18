package com.idealighter.game.service.gold.convert;

import com.idealighter.game.dao.data.po.SendGoldRecord;
import com.idealighter.game.dao.logger.po.GameGoldLog;
import com.idealighter.game.dao.logger.po.GoldLog;
import com.idealighter.game.dao.logger.po.SafeGoldLog;
import com.idealighter.game.dao.logger.po.TransferMoneyGoldLog;
import com.idealighter.game.service.gold.bo.GameGoldLogBo;
import com.idealighter.game.service.gold.bo.GoldLogBo;
import com.idealighter.game.service.gold.bo.SafeGoldLogBo;
import com.idealighter.game.service.gold.bo.SendGoldRecordBo;
import com.idealighter.game.service.gold.bo.TransferMoneyGoldLogBo;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper
public interface GoldBoConvert {
  SendGoldRecordBo toSendGoldRecordBo(SendGoldRecord record);

  SendGoldRecord toSendGoldRecord(SendGoldRecordBo bo);

  List<SendGoldRecordBo> toSendGoldRecordBos(List<SendGoldRecord> records);

  GoldLogBo toGoldLogBo(GoldLog goldLog);

  List<GoldLogBo> toGoldLogBos(List<GoldLog> goldLogs);

  SafeGoldLogBo toSafeGoldLogBo(SafeGoldLog safeGoldLog);

  List<SafeGoldLogBo> toSafeGoldLogBos(List<SafeGoldLog> safeGoldLogs);

  TransferMoneyGoldLogBo toTransferMoneyGoldLogBo(TransferMoneyGoldLog transferMoneyGoldLog);

  List<TransferMoneyGoldLogBo> toTransferMoneyGoldLogBos(
      List<TransferMoneyGoldLog> transferMoneyGoldLogs);

  GameGoldLogBo toGameGoldBo(GameGoldLog gameGoldLog);

  List<GameGoldLogBo> toGameGoldLogBos(List<GameGoldLog> gameGoldLogs);
}
