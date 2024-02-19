package com.idealighter.game.core.dao.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.dao.generate.domain.MailDomain;
import com.idealighter.game.core.dao.generate.domain.MailDomainExample;
import com.idealighter.game.core.dao.mapper.MailMapper;

import java.util.List;

@Singleton
public class MailDao {
  private MailMapper mapper;

  @Inject
  public MailDao(MailMapper mapper) {
    this.mapper = mapper;
  }

  /**
   * 查询列表.
   * 
   * @Title selectByLimit.
   * @author houdongsheng
   * @date 2018年1月12日 下午4:56:48
   * @param playerId 玩家编号
   * @param limit 最高条数
   * @return 邮件列表.
   */
  public List<MailDomain> selectByLimit(long playerId, int limit) {
    MailDomainExample example = new MailDomainExample();
    example.setOrderByClause("create_time desc, id desc");
    example.createCriteria().andPlayerIdEqualTo(playerId);
    example.or(example.createCriteria().andPlayerIdEqualTo((long) 0));
    example.setLimit(limit);

    return mapper.selectByExampleWithBLOBs(example);
  }
}
