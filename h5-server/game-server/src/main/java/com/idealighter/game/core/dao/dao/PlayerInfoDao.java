package com.idealighter.game.core.dao.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.dao.generate.domain.PlayerInfoDomain;
import com.idealighter.game.core.dao.generate.domain.PlayerInfoDomainExample;
import com.idealighter.game.core.dao.mapper.PlayerInfoMapper;
import com.idealighter.utils.check.EmptyUtil;

import java.util.List;

@Singleton
public class PlayerInfoDao {
  private PlayerInfoMapper mapper;

  @Inject
  public PlayerInfoDao(PlayerInfoMapper mapper) {
    this.mapper = mapper;
  }

  public Integer insertSelective(PlayerInfoDomain record) {
    return mapper.insertSelective(record);
  }

  /**
   * 更新玩家信息.
   * 
   * @Title updatePlayerInfoByPlayerId.
   * @author houdongsheng
   * @date 2018年1月12日 下午5:00:12
   * @param playerId 玩家编号
   * @param record 玩家信息
   * @return 更新数量
   */
  public int updatePlayerInfoByPlayerId(long playerId, PlayerInfoDomain record) {
    PlayerInfoDomainExample updateExample = new PlayerInfoDomainExample();
    updateExample.createCriteria().andPlayerIdEqualTo(playerId);

    return mapper.updateByExampleSelective(record, updateExample);
  }

  /**
   * 获取玩家信息.
   * 
   * @Title selectPlayerInfoDomain.
   * @author houdongsheng
   * @date 2018年1月12日 下午5:00:59
   * @param id 用户编号
   * @return PlayerInfoDomain
   */
  public PlayerInfoDomain selectPlayerInfoDomain(long id) {
    PlayerInfoDomain infoDomain = null;
    PlayerInfoDomainExample infoSelect = new PlayerInfoDomainExample();
    infoSelect.createCriteria().andPlayerIdEqualTo(id);
    List<PlayerInfoDomain> playerInfoDomains = mapper.selectByExample(infoSelect);
    if (EmptyUtil.listIsNotEmpty(playerInfoDomains)) {
      infoDomain = playerInfoDomains.get(0);
    }
    return infoDomain;
  }
}
