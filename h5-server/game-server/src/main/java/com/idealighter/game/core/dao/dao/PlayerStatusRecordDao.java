package com.idealighter.game.core.dao.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.dao.generate.domain.PlayerStatusRecordDomain;
import com.idealighter.game.core.dao.generate.domain.PlayerStatusRecordDomainExample;
import com.idealighter.game.core.dao.mapper.PlayerStatusRecordMapper;
import com.idealighter.utils.check.EmptyUtil;

import java.util.Date;
import java.util.List;

@Singleton
public class PlayerStatusRecordDao {
  private PlayerStatusRecordMapper mapper;

  @Inject
  public PlayerStatusRecordDao(PlayerStatusRecordMapper mapper) {
    this.mapper = mapper;
  }

  /**
   * 获取玩家状态记录.
   * 
   * @Title selectPlayerStatusRecordDomain.
   * @author houdongsheng
   * @date 2018年1月12日 下午5:23:47
   * @param playerId 玩家编号
   * @return PlayerStatusRecordDomain
   */
  public PlayerStatusRecordDomain selectPlayerStatusRecordDomain(long playerId) {
    PlayerStatusRecordDomain playerStatusRecordDomain = null;
    PlayerStatusRecordDomainExample statusRecordSelect = new PlayerStatusRecordDomainExample();
    statusRecordSelect.createCriteria().andPlayerIdEqualTo(playerId);
    List<PlayerStatusRecordDomain> statusRecordDomains = mapper.selectByExample(statusRecordSelect);
    if (EmptyUtil.listIsNotEmpty(statusRecordDomains)) {
      playerStatusRecordDomain = statusRecordDomains.get(0);
    }
    return playerStatusRecordDomain;
  }

  /**
   * 获取活跃用户.
   * 
   * @Title findActivePlayer.
   * @author houdongsheng
   * @date 2018年1月12日 下午5:24:09
   * @param activeTime 在线时间
   * @return List&lt;PlayerStatusRecordDomain&gt;
   */
  public List<PlayerStatusRecordDomain> findActivePlayer(Date activeTime) {
    PlayerStatusRecordDomainExample example = new PlayerStatusRecordDomainExample();
    example.createCriteria().andLoginTimeGreaterThan(activeTime);
    example.or(example.createCriteria().andLogoutTimeGreaterThan(activeTime));
    return mapper.selectByExample(example);
  }

  public Integer insertSelective(PlayerStatusRecordDomain record) {
    return mapper.insertSelective(record);
  }

  /**
   * 更新用户状态信息.
   * 
   * @Title updatePlayerStatusRecordDomain.
   * @Description .
   * @author houdongsheng
   * @date 2018年1月12日 下午5:24:41
   * @param playerId 玩家编号
   * @param record 玩家状态记录
   * @return 更新数量
   */
  public int updatePlayerStatusRecordDomain(long playerId, PlayerStatusRecordDomain record) {
    PlayerStatusRecordDomainExample example = new PlayerStatusRecordDomainExample();
    example.createCriteria().andPlayerIdEqualTo(playerId);
    return mapper.updateByExampleSelective(record, example);
  }
}
