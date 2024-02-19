package com.idealighter.game.core.dao.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.dao.generate.domain.PlayerGameInfoDomainExample;
import com.idealighter.game.core.dao.generate.domain.PlayerGameInfoDomainWithBLOBs;
import com.idealighter.game.core.dao.mapper.PlayerGameInfoMapper;
import com.idealighter.utils.check.EmptyUtil;

import java.util.List;

@Singleton
public class PlayerGameInfoDao {
  private PlayerGameInfoMapper mapper;

  @Inject
  public PlayerGameInfoDao(PlayerGameInfoMapper mapper) {
    this.mapper = mapper;
  }

  /**
   * 根据玩家编号，获取游戏信息.
   * 
   * @Title selectPlayerGameInfoDomain.
   * @author houdongsheng
   * @date 2018年1月12日 下午4:57:51
   * @param id 玩家编号
   * @return PlayerGameInfoDomain
   */
  public PlayerGameInfoDomainWithBLOBs selectPlayerGameInfoDomain(long id) {
    PlayerGameInfoDomainWithBLOBs gameInfoDomain = null;
    PlayerGameInfoDomainExample gameInfoSelect = new PlayerGameInfoDomainExample();
    gameInfoSelect.createCriteria().andPlayerIdEqualTo(id);
    List<PlayerGameInfoDomainWithBLOBs> playerGameInfoDomains =
        mapper.selectByExampleWithBLOBs(gameInfoSelect);

    if (EmptyUtil.listIsNotEmpty(playerGameInfoDomains)) {
      gameInfoDomain = playerGameInfoDomains.get(0);
    }
    return gameInfoDomain;
  }

  public Integer insertSelective(PlayerGameInfoDomainWithBLOBs record) {
    return mapper.insertSelective(record);
  }

  /**
   * 更新用户游戏信息.
   * 
   * @Title updatePlayerGameInfoDomain.
   * @author houdongsheng
   * @date 2018年1月12日 下午4:58:34
   * @param playerId 游戏编号
   * @param playerGameInfoDomain 游戏信息
   * @return 更新数量
   */
  public int updatePlayerGameInfoDomain(long playerId,
      PlayerGameInfoDomainWithBLOBs playerGameInfoDomain) {
    PlayerGameInfoDomainExample updateExample = new PlayerGameInfoDomainExample();
    updateExample.createCriteria().andPlayerIdEqualTo(playerId);

    return mapper.updateByExampleSelective(playerGameInfoDomain, updateExample);
  }
}
