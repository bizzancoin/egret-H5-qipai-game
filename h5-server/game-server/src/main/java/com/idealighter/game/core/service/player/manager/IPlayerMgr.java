package com.idealighter.game.core.service.player.manager;

import com.idealighter.game.core.service.log.core.LogReason;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.server.event.ShutdownEvent;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public interface IPlayerMgr {
  void registerPlayer(Player player);

  void unregisterPlayer(Player player);

  Player getPlayer(long playerId);

  int onLinePlayersNum();

  boolean onLine(long playerId);

  ConcurrentHashMap<Long, Player> onLinePlayers();

  void asyncUpdateOnline(long playerId, boolean online);

  void updateOnlinePlayers();

  void updatePlayer(PlayerBo playerDom);

  void asyncUpdatePlayer(Player player);

  void asyncUpdateSuperId(Player player);

  void insertPlayer(Player player);

  void addVipDuration(Player player, int duration);

  void addIngot(Player player, long ingot, LogReason reason);

  void addIngot(PlayerBo playerDomain, long ingot, LogReason reason);

  void addExp(Player player, long exp);

  void addExp(PlayerBo playerDom, long exp);

  void minusIngot(Player player, int ingot, LogReason reason);

  void addGold(Player player, long gold, LogReason reason);

  void addGold(Player player, long gold, boolean log, boolean notice, LogReason reason);

  void addGold(long playerId, long gold, LogReason reason);

  void addGold(PlayerBo playerDom, long gold, LogReason reason);

  void addGold(PlayerBo playerDom, long gold, boolean log, LogReason reason);

  void noticeGold(Player player);

  void addWinGold(Player player, long gold);

  void addWinGold(PlayerBo playerBo, long gold);

  void minusGold(Player player, long gold, LogReason reason);

  void addSafeGold(Player player, long gold, String orderNo, LogReason reason);

  boolean addSafeGold(Player player, long beforeSafeGold, long afterSafeGold, String orderNo,
      LogReason reason);

  void addSafeGold(PlayerBo playerBo, long gold, String orderNo, LogReason reason);

  boolean addSafeGold(PlayerBo playerBo, long beforeSafeGold, long afterSafeGold, String orderNo,
      LogReason reason);

  void changeUserTypeAndSuperId(Player player, long superId, byte playerType);

  void addCredit(Player player, long credit, LogReason reason);

  void addCredit(PlayerBo playerDom, long credit, LogReason reason);

  void minusCredit(Player player, int credit, LogReason reason);

  boolean recharge(long playerId, String orderid, String payway, String paywayname, long payamount,
      long paygold, String providerorderid);

  PlayerBo selectPlayer(long playerId);

  PlayerBo selectPlayer(String userName);

  void updatePlayerGameInfo(Long playerId);

  PlayerBo selectPlayerBySuperId(long superId);

  PlayerBo selectPlayerByPhone(String phone);

  PlayerBo selectPlayerByUnionId(String unionId);

  Collection<PlayerBo> activePlayers();

  void onShutDown(ShutdownEvent event);
}
