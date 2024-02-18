package com.idealighter.game.service.bank;

import com.idealighter.game.common.ResultPage;
import com.idealighter.game.dao.data.dao.TransferGoldRecordDao;
import com.idealighter.game.dao.data.po.TransferGoldRecord;
import com.idealighter.game.dao.data.po.TransferGoldRecordExample;
import com.idealighter.game.service.bank.bo.TransferGoldRecordBo;
import com.idealighter.game.service.bank.convert.BankBoConvert;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class BankService implements IBankService {

  @Autowired
  private TransferGoldRecordDao transferGoldRecordDao;

  @Autowired
  private BankBoConvert bankBoConvert;

  @Override
  public ResultPage<TransferGoldRecordBo> findByPage(Long playerId, Long superId, Byte playerType,
      Byte type, Byte oppositeType, Date startTime, Date endTime, int page, int pageSize) {

    TransferGoldRecordExample select = new TransferGoldRecordExample();

    TransferGoldRecordExample.Criteria criteria = select.createCriteria();

    if (playerId != null) {
      criteria.andPlayerIdEqualTo(playerId);
    }

    if (superId != null) {
      criteria.andPlayerSuperIdEqualTo(superId);
    }

    if (playerType != null) {
      criteria.andPlayerTypeEqualTo(playerType);
    }

    if (type != null) {
      criteria.andTypeEqualTo(type);
    }

    if (oppositeType != null) {
      criteria.andOppositeTypeEqualTo(oppositeType);
    }
    if (startTime != null) {
      criteria.andCreateTimeGreaterThanOrEqualTo(startTime);
    }
    if (endTime != null) {
      criteria.andCreateTimeLessThan(endTime);
    }

    select.setOffset((page - 1) * pageSize);
    select.setLimit(pageSize);

    select.setOrderByClause("create_time desc, id desc");

    List<TransferGoldRecord> records = transferGoldRecordDao.selectByExample(select);

    List<TransferGoldRecordBo> bos = bankBoConvert.toTransferGoldRecordBos(records);

    long total = transferGoldRecordDao.countByExample(select);

    ResultPage<TransferGoldRecordBo> resultPage = new ResultPage<>();
    resultPage.setList(bos);
    resultPage.setTotal(total);

    return resultPage;
  }

  @Override
  public long countTotalGold(Long playerId, Long superId, Byte playerType, Byte type,
      Byte oppositeType, Date startTime, Date endTime) {

    return transferGoldRecordDao.countTotalGold(playerId, superId, playerType, type, oppositeType,
        startTime, endTime);
  }

}
