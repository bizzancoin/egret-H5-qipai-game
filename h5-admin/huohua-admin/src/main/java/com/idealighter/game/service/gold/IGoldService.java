package com.idealighter.game.service.gold;

import com.idealighter.game.common.ResultPage;
import com.idealighter.game.common.ResultPageTotal;
import com.idealighter.game.service.gold.bo.DailyBo;
import com.idealighter.game.service.gold.bo.GameGoldLogBo;
import com.idealighter.game.service.gold.bo.GoldLogBo;
import com.idealighter.game.service.gold.bo.SafeGoldLogBo;
import com.idealighter.game.service.gold.bo.SendGoldRecordBo;
import com.idealighter.game.service.gold.bo.TransferMoneyGoldLogBo;

import java.util.Date;

public interface IGoldService {

  boolean addSendGoldRecord(SendGoldRecordBo bo);

  ResultPageTotal<SendGoldRecordBo> findGoldRecordByPage(Integer adminId, Long playerId,
      Long superId, Date startTime, Date endTime, String channelId, int page, int pageSize);

  ResultPage<GoldLogBo> findGoldLogByPage(Long playerId, Long superId, Date time, String channelId,
      int page, int pageSize);

  ResultPage<SafeGoldLogBo> findSafeGoldLogByPage(Long playerId, Long superId, Date time,
      String channelId, String orderNo, int page, int pageSize);

  ResultPageTotal<TransferMoneyGoldLogBo> findTransferLogByPage(Long playerId, Long superId,
      Date time, Byte flag, String channelId, String orderNo, int page, int pageSize);

  ResultPageTotal<GameGoldLogBo> findGameGoldByPage(Long playerId, Long superId, Date time,
      String channelId, int page, int pageSize);

  ResultPageTotal<DailyBo> findDailyByPage(Long playerId, Long superId, String channelId,
      Date startTime, Date endTime, int page, int pageSize);
}
