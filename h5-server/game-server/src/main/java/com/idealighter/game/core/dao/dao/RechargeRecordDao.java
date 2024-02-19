package com.idealighter.game.core.dao.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.dao.generate.domain.RechargeRecordDomain;
import com.idealighter.game.core.dao.generate.domain.RechargeRecordDomainExample;
import com.idealighter.game.core.dao.mapper.RechargeRecordMapper;
import com.idealighter.utils.check.EmptyUtil;

import java.util.List;

@Singleton
public class RechargeRecordDao {
  private RechargeRecordMapper mapper;

  @Inject
  public RechargeRecordDao(RechargeRecordMapper mapper) {
    this.mapper = mapper;
  }

  public Integer addRechargeRecord(RechargeRecordDomain record) {
    return mapper.insertSelective(record);
  }

  /**
   * 获取充值记录.
   * 
   * @Title selectRechargeRecordByOrderNo.
   * @author houdongsheng
   * @date 2018年1月12日 下午5:26:41
   * @param tradeNo 订单编号
   * @return RechargeRecordDomain
   */
  public RechargeRecordDomain selectRechargeRecordByOrderNo(String tradeNo) {
    RechargeRecordDomainExample example = new RechargeRecordDomainExample();
    example.createCriteria().andTradeNoEqualTo(tradeNo);
    example.setLimit(1);
    RechargeRecordDomain result = null;
    List<RechargeRecordDomain> list = mapper.selectByExample(example);
    if (EmptyUtil.listIsNotEmpty(list)) {
      result = list.get(0);
    }
    return result;
  }

  /**
   * 修改充值状态.
   * 
   * @Title changeState.
   * @author houdongsheng
   * @date 2018年1月12日 下午5:27:09
   * @param id 编号
   * @param oldState 久状态
   * @param newState 新状态
   * @return 更新数量
   */
  public Integer changeState(Integer id, Byte oldState, Byte newState) {
    RechargeRecordDomainExample example = new RechargeRecordDomainExample();
    example.createCriteria().andIdEqualTo(id).andStateEqualTo(oldState);
    RechargeRecordDomain record = new RechargeRecordDomain();
    record.setState(newState);
    return mapper.updateByExampleSelective(record, example);
  }
}
