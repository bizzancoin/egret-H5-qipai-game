package com.idealighter.game.core.dao.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.dao.generate.domain.PlayerVipDomain;
import com.idealighter.game.core.dao.generate.domain.PlayerVipDomainExample;
import com.idealighter.game.core.dao.mapper.PlayerVipMapper;
import com.idealighter.utils.check.EmptyUtil;

import java.util.List;

@Singleton
public class PlayerVipDao {
  private PlayerVipMapper mapper;

  @Inject
  public PlayerVipDao(PlayerVipMapper mapper) {
    this.mapper = mapper;
  }

  /**
   * 获取vip.
   * 
   * @Title selectPlayerVipDomain.
   * @author houdongsheng
   * @date 2018年1月12日 下午5:25:30
   * @param playerId 玩家编号
   * @return PlayerVipDomain
   */
  public PlayerVipDomain selectPlayerVipDomain(long playerId) {
    PlayerVipDomain playerVipDomain = null;
    PlayerVipDomainExample vipSelect = new PlayerVipDomainExample();
    vipSelect.createCriteria().andPlayerIdEqualTo(playerId);
    List<PlayerVipDomain> playerVipDomains = mapper.selectByExample(vipSelect);
    if (EmptyUtil.listIsNotEmpty(playerVipDomains)) {
      playerVipDomain = playerVipDomains.get(0);
    }
    return playerVipDomain;
  }

  public Integer insertSelective(PlayerVipDomain record) {
    return mapper.insertSelective(record);
  }

  /**
   * 更新玩家vip信息.
   * 
   * @Title updatePlayerVipDomain.
   * @author houdongsheng
   * @date 2018年1月12日 下午5:26:05
   * @param id 玩家编号
   * @param record vip信息
   * @return 更新数量
   */
  public int updatePlayerVipDomain(long id, PlayerVipDomain record) {
    PlayerVipDomainExample updateExample = new PlayerVipDomainExample();
    updateExample.createCriteria().andPlayerIdEqualTo(id);

    return mapper.updateByExampleSelective(record, updateExample);
  }
}
