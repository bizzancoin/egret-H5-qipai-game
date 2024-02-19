package com.idealighter.game.core.service.player.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.dao.dao.PlayerGameInfoDao;
import com.idealighter.game.core.dao.dao.PlayerInfoDao;
import com.idealighter.game.core.dao.dao.PlayerMainDao;
import com.idealighter.game.core.dao.dao.PlayerSafeInfoDao;
import com.idealighter.game.core.dao.dao.PlayerStatusRecordDao;
import com.idealighter.game.core.dao.dao.PlayerVipDao;
import com.idealighter.game.core.dao.generate.domain.PlayerGameInfoDomainWithBLOBs;
import com.idealighter.game.core.dao.generate.domain.PlayerInfoDomain;
import com.idealighter.game.core.dao.generate.domain.PlayerMainDomain;
import com.idealighter.game.core.dao.generate.domain.PlayerSafeInfoDomain;
import com.idealighter.game.core.dao.generate.domain.PlayerStatusRecordDomain;
import com.idealighter.game.core.dao.generate.domain.PlayerVipDomain;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.service.bo.PlayerRankBo;
import com.idealighter.game.core.service.player.service.convert.PlayerBoConvert;
import com.idealighter.utils.check.EmptyUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mybatis.guice.transactional.Transactional;

@Singleton
public class PlayerService implements IPlayerService {
  private PlayerMainDao playerMainDao;
  private PlayerInfoDao playerInfoDao;
  private PlayerGameInfoDao playerGameInfoDao;
  private PlayerSafeInfoDao playerSafeInfoDao;
  private PlayerVipDao playerVipDao;
  private PlayerStatusRecordDao playerStatusRecordDao;


  /**
   * 构造函数.
   */
  @Inject
  public PlayerService(PlayerMainDao playerMainDao, PlayerGameInfoDao playerGameInfoDao,
      PlayerInfoDao playerInfoDao, PlayerSafeInfoDao playerSafeInfoDao, PlayerVipDao playerVipDao,
      PlayerStatusRecordDao playerStatusRecordDao) {
    this.playerMainDao = playerMainDao;
    this.playerGameInfoDao = playerGameInfoDao;
    this.playerInfoDao = playerInfoDao;
    this.playerSafeInfoDao = playerSafeInfoDao;
    this.playerVipDao = playerVipDao;
    this.playerStatusRecordDao = playerStatusRecordDao;
  }

  private PlayerBo selectPlayerOtherInfo(PlayerMainDomain mainDomain) {
    PlayerBo bo = null;
    if (mainDomain != null && mainDomain.getId() != null) {
      long id = mainDomain.getId();
      PlayerInfoDomain playerInfoDomain = playerInfoDao.selectPlayerInfoDomain(id);

      PlayerGameInfoDomainWithBLOBs playerGameInfoDomain =
          playerGameInfoDao.selectPlayerGameInfoDomain(id);

      PlayerSafeInfoDomain playerSafeInfoDomain =
          playerSafeInfoDao.findPlayerSafeInfoByPlayerId(id);

      PlayerVipDomain playerVipDomain = playerVipDao.selectPlayerVipDomain(id);

      PlayerStatusRecordDomain playerStatusRecordDomain =
          playerStatusRecordDao.selectPlayerStatusRecordDomain(id);

      bo = PlayerBoConvert.INSTANCE.toPlayerBo(mainDomain, playerInfoDomain, playerGameInfoDomain,
          playerSafeInfoDomain, playerVipDomain, playerStatusRecordDomain);
    }

    return bo;
  }

  @Override
  public PlayerBo selectById(long id) {
    PlayerMainDomain playerMainDomain = playerMainDao.findPlayerMainDomainById(id);
    PlayerBo bo = selectPlayerOtherInfo(playerMainDomain);
    return bo;
  }

  @Override
  public PlayerBo selectBySuperId(long superId) {
    PlayerMainDomain playerMainDomain = playerMainDao.findPlayerMainDomainBySuperId(superId);
    PlayerBo bo = selectPlayerOtherInfo(playerMainDomain);
    return bo;
  }

  @Override
  public List<PlayerBo> selectActivePlayers(Date activeTime) {
    List<PlayerStatusRecordDomain> statusRecordDomains =
        playerStatusRecordDao.findActivePlayer(activeTime);
    List<PlayerBo> playerBos = new ArrayList<>();
    if (EmptyUtil.listIsNotEmpty(statusRecordDomains)) {
      for (PlayerStatusRecordDomain statusRecordDomain : statusRecordDomains) {
        PlayerBo bo = selectById(statusRecordDomain.getPlayerId());
        if (bo != null) {
          playerBos.add(bo);
        }
      }
    }
    return playerBos;
  }

  @Override
  public Long selectPlayerIdByPlayerName(String playerName) {
    Long playerId = null;
    PlayerMainDomain playerMainDomain = playerMainDao.findPlayerMainDomainByPlayerName(playerName);
    if (playerMainDomain != null) {
      playerId = playerMainDomain.getId();
    }
    return playerId;
  }

  @Override
  public PlayerBo selectPlayerByUserName(String userName) {
    PlayerMainDomain playerMainDomain = playerMainDao.findPlayerMainDomainByUserName(userName);
    return selectPlayerOtherInfo(playerMainDomain);
  }

  @Override
  public PlayerBo selectPlayerByUnionId(String unionId) {
    PlayerMainDomain playerMainDomain = playerMainDao.findPlayerMainDomainByUnionId(unionId);
    return selectPlayerOtherInfo(playerMainDomain);
  }

  @Override
  public PlayerBo selectPlayerByPhone(String phone) {
    PlayerMainDomain playerMainDomain = playerMainDao.findPlayerMainDomainByPhone(phone);
    return selectPlayerOtherInfo(playerMainDomain);
  }

  @Transactional
  @Override
  public int insert(PlayerBo playerBo) {
    PlayerMainDomain playerMainDomain = PlayerBoConvert.INSTANCE.toPlayerMainDomain(playerBo);
    int out = playerMainDao.insertSelective(playerMainDomain);

    if (out > 0 && playerMainDomain.getId() > 0) {
      long id = playerMainDomain.getId();
      playerBo.setId(id);
      playerBo.setSuperId(id);

      PlayerInfoDomain playerInfoDomain = PlayerBoConvert.INSTANCE.toPlayerInfoDomain(playerBo);

      playerInfoDomain.setPlayerId(id);

      playerInfoDao.insertSelective(playerInfoDomain);


      PlayerGameInfoDomainWithBLOBs playerGameInfoDomain =
          PlayerBoConvert.INSTANCE.toPlayerGameInfoDomain(playerBo);

      playerGameInfoDomain.setPlayerId(id);

      playerGameInfoDao.insertSelective(playerGameInfoDomain);

      PlayerSafeInfoDomain playerSafeInfoDomain =
          PlayerBoConvert.INSTANCE.toPlayerSafeInfoDomain(playerBo);

      playerSafeInfoDomain.setPlayerId(id);

      playerSafeInfoDao.insertSelective(playerSafeInfoDomain);


      PlayerVipDomain playerVipDomain = PlayerBoConvert.INSTANCE.toPlayerVipDomain(playerBo);

      playerVipDomain.setPlayerId(id);

      playerVipDao.insertSelective(playerVipDomain);

      PlayerStatusRecordDomain playerStatusRecordDomain =
          PlayerBoConvert.INSTANCE.toPlayerStatusRecordDomain(playerBo);
      playerStatusRecordDomain.setPlayerId(id);
      playerStatusRecordDao.insertSelective(playerStatusRecordDomain);
    }

    return out;
  }

  private int updatePlayerInfoByPlayerId(long id, PlayerBo playerBo) {
    PlayerInfoDomain playerInfoDomain = PlayerBoConvert.INSTANCE.toPlayerInfoDomain(playerBo);
    return playerInfoDao.updatePlayerInfoByPlayerId(id, playerInfoDomain);
  }

  @Override
  public int updatePlayerGameInfoDomain(PlayerBo playerBo) {
    int result = 0;
    Long id = playerBo.getId();
    if (id != null && id.compareTo(0L) > 0) {
      result = updatePlayerGameInfoDomain(id, playerBo);
    }
    return result;
  }

  private int updatePlayerGameInfoDomain(long id, PlayerBo playerBo) {
    PlayerGameInfoDomainWithBLOBs playerGameInfoDomain =
        PlayerBoConvert.INSTANCE.toPlayerGameInfoDomain(playerBo);

    return playerGameInfoDao.updatePlayerGameInfoDomain(id, playerGameInfoDomain);
  }

  private int updatePlayerSafeInfoDomain(long id, PlayerBo playerBo) {
    PlayerSafeInfoDomain record = PlayerBoConvert.INSTANCE.toPlayerSafeInfoDomain(playerBo);

    return playerSafeInfoDao.updatePlayerSafeInfo(id, record);
  }

  private int updatePlayerVipDomain(long id, PlayerBo playerBo) {
    PlayerVipDomain playerGameInfoDomain = PlayerBoConvert.INSTANCE.toPlayerVipDomain(playerBo);
    return playerVipDao.updatePlayerVipDomain(id, playerGameInfoDomain);
  }

  private int updatePlayerStatusRecordDomain(long id, PlayerBo playerBo) {
    PlayerStatusRecordDomain playerStatusRecordDomain =
        PlayerBoConvert.INSTANCE.toPlayerStatusRecordDomain(playerBo);
    return playerStatusRecordDao.updatePlayerStatusRecordDomain(id, playerStatusRecordDomain);
  }

  @Transactional
  @Override
  public int updateById(PlayerBo playerBo) {
    PlayerMainDomain playerMainDomain = PlayerBoConvert.INSTANCE.toPlayerMainDomain(playerBo);

    int out = playerMainDao.updateByPrimaryKeySelective(playerMainDomain);

    if (out > 0 && playerMainDomain.getId() > 0) {
      long id = playerMainDomain.getId();

      updatePlayerInfoByPlayerId(id, playerBo);

      updatePlayerGameInfoDomain(id, playerBo);

      updatePlayerSafeInfoDomain(id, playerBo);

      updatePlayerVipDomain(id, playerBo);

      updatePlayerStatusRecordDomain(id, playerBo);

    }

    return out;
  }

  @Override
  public int updateOnlineByPlayerId(long id, boolean online) {
    PlayerMainDomain playerMainDomain = new PlayerMainDomain();
    playerMainDomain.setId(id);
    playerMainDomain.setOnline(online);

    return playerMainDao.updateByPrimaryKeySelective(playerMainDomain);
  }

  @Override
  public int updateSuperIdByPlayerId(long id, long superId) {
    PlayerMainDomain playerMainDomain = new PlayerMainDomain();
    playerMainDomain.setId(id);
    playerMainDomain.setSuperId(superId);
    return playerMainDao.updateByPrimaryKeySelective(playerMainDomain);
  }

  @Override
  public int resetOnline() {
    return playerMainDao.resetOnline();
  }

  @Override
  public List<PlayerRankBo> selectBeyoudRankList(long gold, int limit) {
    List<PlayerMainDomain> playerMainDomains = playerMainDao.selectBeyoudRankList(gold, limit);
    return PlayerBoConvert.INSTANCE.toPlayerRankBos(playerMainDomains);
  }

  @Override
  public List<PlayerRankBo> selectBelowRankList(long gold, int limit) {
    List<PlayerMainDomain> playerMainDomains = playerMainDao.selectBelowRankList(gold, limit);
    return PlayerBoConvert.INSTANCE.toPlayerRankBos(playerMainDomains);
  }

  @Override
  public List<PlayerRankBo> selectTopWinGold(int limit) {
    List<PlayerMainDomain> playerMainDomains = playerMainDao.selectTopWinGoldRankList(limit);
    return PlayerBoConvert.INSTANCE.toPlayerRankBos(playerMainDomains);
  }

  @Override
  public boolean existsSuperId(Long superId) {

    return playerMainDao.existsSuperId(superId);
  }

}
