package com.idealighter.game.core.dao.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.dao.generate.domain.PlayerSafeInfoDomain;
import com.idealighter.game.core.dao.generate.domain.PlayerSafeInfoDomainExample;
import com.idealighter.game.core.dao.mapper.PlayerSafeInfoMapper;
import com.idealighter.utils.check.EmptyUtil;

import java.util.List;

@Singleton
public class PlayerSafeInfoDao {
  private PlayerSafeInfoMapper mapper;

  @Inject
  public PlayerSafeInfoDao(PlayerSafeInfoMapper mapper) {
    this.mapper = mapper;
  }

  /**
   * 获取玩家安全信息.
   * 
   * @Title findPlayerSafeInfoByPlayerId.
   * @author houdongsheng
   * @date 2018年1月12日 下午5:21:45
   * @param playerId 玩家列表
   * @return PlayerSafeInfoDomain
   */
  public PlayerSafeInfoDomain findPlayerSafeInfoByPlayerId(long playerId) {
    PlayerSafeInfoDomain playerSafeInfoDomain = null;
    PlayerSafeInfoDomainExample safeInfoSelect = new PlayerSafeInfoDomainExample();
    safeInfoSelect.createCriteria().andPlayerIdEqualTo(playerId);
    List<PlayerSafeInfoDomain> playerSafeInfoDomains = mapper.selectByExample(safeInfoSelect);

    if (EmptyUtil.listIsNotEmpty(playerSafeInfoDomains)) {
      playerSafeInfoDomain = playerSafeInfoDomains.get(0);
    }
    return playerSafeInfoDomain;
  }

  public Integer insertSelective(PlayerSafeInfoDomain record) {
    return mapper.insertSelective(record);
  }

  /**
   * 更新玩家安全中心数据.
   * 
   * @Title updatePlayerSafeInfo.
   * @author houdongsheng
   * @date 2018年1月12日 下午5:22:09
   * @param playerId 玩家编号
   * @param record 完全中心数据
   * @return 更新数量
   */
  public Integer updatePlayerSafeInfo(Long playerId, PlayerSafeInfoDomain record) {
    PlayerSafeInfoDomainExample updateExample = new PlayerSafeInfoDomainExample();
    updateExample.createCriteria().andPlayerIdEqualTo(playerId);
    return mapper.updateByExampleSelective(record, updateExample);
  }
}
