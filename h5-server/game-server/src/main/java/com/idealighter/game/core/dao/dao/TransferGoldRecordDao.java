package com.idealighter.game.core.dao.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.dao.TransferTotal;
import com.idealighter.game.core.dao.generate.domain.TransferGoldRecordDomain;
import com.idealighter.game.core.dao.generate.domain.TransferGoldRecordDomainExample;
import com.idealighter.game.core.dao.mapper.TransferGoldRecordMapper;

import java.util.Date;
import java.util.List;

@Singleton
public class TransferGoldRecordDao {
  private TransferGoldRecordMapper mapper;

  @Inject
  public TransferGoldRecordDao(TransferGoldRecordMapper mapper) {
    this.mapper = mapper;
  }

  public Integer insertSelective(TransferGoldRecordDomain record) {
    return mapper.insertSelective(record);
  }

  /**
   * 查询总数量.
   * 
   * @Title countNextTotal.
   * @author houdongsheng
   * @date 2018年1月12日 下午5:28:20
   * @param playerId 玩家编号
   * @param lastId 最后数据的编号
   * @return 数量
   */
  public long countNextTotal(long playerId, Integer lastId, Byte oppositeType) {
    TransferGoldRecordDomainExample example = new TransferGoldRecordDomainExample();
    TransferGoldRecordDomainExample.Criteria criteria = example.createCriteria();
    criteria.andPlayerIdEqualTo(playerId);
    if (lastId != null) {
      criteria.andIdLessThan(lastId);
    }
    if (oppositeType != null) {
      criteria.andOppositeTypeEqualTo(oppositeType);
    }
    return mapper.countByExample(example);
  }

  /**
   * 向后分页.
   * 
   * @Title selectNextByPage.
   * @author houdongsheng
   * @date 2018年1月12日 下午5:28:54
   * @param playerId 玩家编号
   * @param id 最后数据的编号
   * @param limit 每页大小
   * @return List&lt;TransferGoldRecordDomain&gt;
   */
  public List<TransferGoldRecordDomain> selectNextByPage(Long playerId, Integer id,
      Byte oppositeType, int limit) {
    TransferGoldRecordDomainExample example = new TransferGoldRecordDomainExample();
    TransferGoldRecordDomainExample.Criteria criteria = example.createCriteria();
    if (playerId != null) {
      criteria.andPlayerIdEqualTo(playerId);
    }
    if (id != null) {
      criteria.andIdLessThan(id);
    }
    if (oppositeType != null) {
      criteria.andOppositeTypeEqualTo(oppositeType);
    }
    example.setOrderByClause("id desc");
    example.setLimit(limit);
    return mapper.selectByExample(example);
  }

  /**
   * 向前分页.
   * 
   * @Title selectPreByPage.
   * @author houdongsheng
   * @date 2018年1月12日 下午5:30:28
   * @param playerId 玩家编号
   * @param id 最后数据的编号
   * @param limit 每页大小
   * @return List&lt;TransferGoldRecordDomain&gt;
   */
  public List<TransferGoldRecordDomain> selectPreByPage(Long playerId, Integer id,
      Byte oppositeType, int limit) {
    TransferGoldRecordDomainExample example = new TransferGoldRecordDomainExample();
    TransferGoldRecordDomainExample.Criteria criteria = example.createCriteria();
    if (playerId != null) {
      criteria.andPlayerIdEqualTo(playerId);
    }
    if (id != null) {
      criteria.andIdGreaterThan(id);
    }
    if (oppositeType != null) {
      criteria.andOppositeTypeEqualTo(oppositeType);
    }
    example.setOrderByClause("id desc");
    example.setLimit(limit);
    return mapper.selectByExample(example);
  }

  /**
   * 统计数量.
   * 
   * @Title countTotalSum.
   * @author houdongsheng
   * @date 2018年1月12日 下午5:30:55
   * @param playerId 玩家编号
   * @return 总数量
   */
  public long countTotalSum(long playerId, Byte oppositeType) {
    TransferGoldRecordDomainExample example = new TransferGoldRecordDomainExample();
    TransferGoldRecordDomainExample.Criteria criteria = example.createCriteria();
    criteria.andPlayerIdEqualTo(playerId);
    if (oppositeType != null) {
      criteria.andOppositeTypeEqualTo(oppositeType);
    }
    return mapper.countByExample(example);
  }

  public List<TransferTotal> sumTotal(long playerId, Date beginTime, Date endTime) {
    return mapper.sumTotal(playerId, beginTime, endTime);
  }
}
