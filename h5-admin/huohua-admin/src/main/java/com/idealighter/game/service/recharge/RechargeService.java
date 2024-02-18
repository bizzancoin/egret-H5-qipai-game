package com.idealighter.game.service.recharge;

import com.idealighter.game.common.ResultPage;
import com.idealighter.game.dao.data.mapper.RechargeRecordMapper;
import com.idealighter.game.dao.data.po.RechargeRecord;
import com.idealighter.game.dao.data.po.RechargeRecordExample;
import com.idealighter.game.service.recharge.bo.RechargeBo;
import com.idealighter.game.service.recharge.convert.RechargeBoConvert;
import com.idealighter.utils.check.EmptyUtil;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RechargeService implements IRechargeService {

  @Autowired
  private RechargeRecordMapper rechargeRecordMapper;

  @Autowired
  private RechargeBoConvert rechargeBoConvert;

  @Override
  public ResultPage<RechargeBo> findByPage(String trandeNo, Byte payType, Long playerId, Byte state,
      Date startTime, Date endTime, int page, int pageSize) {
    RechargeRecordExample example = new RechargeRecordExample();

    example.setOffset((page - 1) * pageSize);
    example.setLimit(pageSize);

    example.setOrderByClause("create_time desc, id desc");

    RechargeRecordExample.Criteria criteria = example.createCriteria();

    if (EmptyUtil.stringIsNotEmpty(trandeNo)) {
      criteria.andTradeNoEqualTo(trandeNo);
    }
    if (payType != null) {
      criteria.andPayTypeEqualTo(payType);
    }
    if (playerId != null) {
      criteria.andPlayerIdEqualTo(playerId);
    }
    if (state != null) {
      criteria.andStateEqualTo(state);
    }
    if (startTime != null) {
      criteria.andCreateTimeGreaterThanOrEqualTo(startTime);
    }
    if (endTime != null) {
      criteria.andCreateTimeLessThanOrEqualTo(endTime);
    }

    List<RechargeRecord> rechargeRecords = rechargeRecordMapper.selectByExample(example);

    long total = rechargeRecordMapper.countByExample(example);

    List<RechargeBo> rechargeBos = rechargeBoConvert.toRechargeBos(rechargeRecords);

    ResultPage<RechargeBo> resultPage = new ResultPage<>();
    resultPage.setList(rechargeBos);
    resultPage.setTotal(total);

    return resultPage;
  }

}
