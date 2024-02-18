package com.idealighter.game.service.bank;

import com.idealighter.game.common.ResultPage;
import com.idealighter.game.service.bank.bo.TransferGoldRecordBo;

import java.util.Date;


public interface IBankService {
  ResultPage<TransferGoldRecordBo> findByPage(Long playerId, Long superId, Byte playerType,
      Byte type, Byte oppositeType, Date startTime, Date endTime, int page, int pageSize);


  long countTotalGold(Long playerId, Long superId, Byte playerType, Byte type, Byte oppositeType,
      Date startTime, Date endTime);
}
