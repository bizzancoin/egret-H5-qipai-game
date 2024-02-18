package com.idealighter.game.service.recharge;

import com.idealighter.game.common.ResultPage;
import com.idealighter.game.service.recharge.bo.RechargeBo;

import java.util.Date;

public interface IRechargeService {

  ResultPage<RechargeBo> findByPage(String trandeNo, Byte payType, Long playerId, Byte state,
      Date startTime, Date endTime, int page, int pageSize);
}
