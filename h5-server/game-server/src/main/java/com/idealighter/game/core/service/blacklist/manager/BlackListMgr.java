package com.idealighter.game.core.service.blacklist.manager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.dao.dao.BlackListDao;
import com.idealighter.game.core.dao.generate.domain.BlackListDomain;
import com.idealighter.game.core.service.login.manager.LoginMgr;
import com.idealighter.game.core.service.player.manager.PlayerMgr;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.struct.Player;

import java.util.List;

/**
 * 黑名单管理 .
 * 
 * @date 2015年11月19日 下午4:26:24
 *
 */
@Singleton
public class BlackListMgr {

  private static final String IP_LIMIT = "ip";
  private static final String MAC_LIMIT = "mac";
  private static final String PLAYER_LIMIT = "player";

  private static final String LOGIN_ACTION = "login";
  private static final String REGISTER_ACTION = "register";
  // 玩游戏
  private static final String PLAY_ACTION = "play";
  private BlackListDao blackListDao;
  // 黑名单
  private List<BlackListDomain> blacklists;
  @Inject
  private PlayerMgr playerMgr;
  @Inject
  private LoginMgr loginMgr;

  @Inject
  public BlackListMgr(BlackListDao blackListDao) {
    this.blackListDao = blackListDao;
    loadData();
  }

  /**
   * 加载数据 .
   */
  public void loadData() {
    this.blacklists = blackListDao.selectAll();
  }

  /**
   * 是否可以玩游戏 .
   * 
   * @param player 玩家.
   * @return
   */
  public boolean playAble(Player player) {
    return actionAble(PLAY_ACTION, null, null, player.getId());
  }

  /**
   * 是否能够登录 .
   * 
   * @param ip .
   * @param mac .
   * @return
   */
  public boolean loginAble(String ip, String mac) {
    return actionAble(LOGIN_ACTION, ip, mac, -1);
  }

  /**
   * 是否能够注册 .
   * 
   * @param ip .
   * @param mac .
   * @return
   */
  public boolean registerAble(String ip, String mac) {
    return actionAble(REGISTER_ACTION, ip, mac, -1);
  }

  /**
   * 动作是否可行 .
   * 
   * @param action .
   * @param ip .
   * @param mac .
   * @param playerId 玩家id.
   * @return
   */
  private boolean actionAble(String action, String ip, String mac, long playerId) {
    long now = System.currentTimeMillis();
    for (BlackListDomain domain : blacklists) {
      if (domain.getEndTime().getTime() > now && domain.getLimitAction().equals(action)) {
        if (domain.getLimitType().equals(IP_LIMIT) && domain.getLimitValue().equals(ip)) {
          return false;
        }

        if (domain.getLimitType().equals(MAC_LIMIT) && domain.getLimitValue().equals(mac)) {
          return false;
        }

        if (domain.getLimitType().equals(PLAYER_LIMIT)
            && domain.getLimitValue().equals("" + playerId)) {
          return false;
        }
      }
    }

    return true;
  }


  /**
   * 冻结玩家 .
   * 
   * @param playerId 玩家id.
   */
  public PlayerBo lockPlayer(long playerId) {
    Player player = playerMgr.getPlayer(playerId);
    if (player == null) {
      PlayerBo playerDom = playerMgr.selectPlayer(playerId);
      playerDom.setLocked(true);
      return playerDom;
    } else {
      player.setLocked(true);
      loginMgr.noticeLogoutForLocked(player);
      return player.playerBo();
    }
  }

  /**
   * 解冻玩家 .
   * 
   * @param playerId 玩家id.
   */
  public PlayerBo unLockPlayer(long playerId) {
    Player player = playerMgr.getPlayer(playerId);
    if (player == null) {
      PlayerBo playerDom = playerMgr.selectPlayer(playerId);
      playerDom.setLocked(false);
      return playerDom;
    } else {
      player.setLocked(false);
      return player.playerBo();
    }
  }

}
