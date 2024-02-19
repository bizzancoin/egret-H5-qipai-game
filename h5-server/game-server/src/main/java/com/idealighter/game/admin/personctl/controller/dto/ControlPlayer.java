package com.idealighter.game.admin.personctl.controller.dto;

import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.core.service.prizepool.struct.player.PlayerPrizePool;

import java.util.ArrayList;
import java.util.List;

/**
 * 控制玩家信息 .
 * 
 * @date 2015年10月22日 下午6:52:16
 *
 */
public class ControlPlayer {

  // 玩家id
  private long playerId;
  // 玩家名称
  private String playerName;
  // 位置
  private String position;
  // 个人控制剩余gold
  private long controlSurplus;
  // 玩家个人奖池
  private List<PlayerPrizePool> prizePools = new ArrayList<>();
  // 身上金币
  private long gold;
  // 保险箱金币
  private long safeGold;

  /**
   * 构造函数.
   * 
   * @param domain .
   */
  public ControlPlayer(PlayerBo domain) {
    this.playerId = domain.getId();
    this.playerName = domain.getPlayerName();

    for (PlayerPrizePool prizePool : domain.prizePools().values()) {
      this.controlSurplus += prizePool.getPrize().get();
      prizePools.add(prizePool);
    }

    this.gold = domain.getGold().get();
    this.safeGold = domain.getSafeGold().get();
  }

  /**
   * 构造函数.
   * 
   * @param player 玩家.
   */
  public ControlPlayer(Player player) {
    this(player.playerBo());
    AbstractRoom room = player.curRoom;
    if (room != null) {
      this.position = room.game().getDesc() + "-" + room.getName();
    }
  }

  public long getPlayerId() {
    return playerId;
  }

  public void setPlayerId(long playerId) {
    this.playerId = playerId;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public long getControlSurplus() {
    return controlSurplus;
  }

  public void setControlSurplus(long controlSurplus) {
    this.controlSurplus = controlSurplus;
  }

  public long getGold() {
    return gold;
  }

  public void setGold(long gold) {
    this.gold = gold;
  }

  public long getSafeGold() {
    return safeGold;
  }

  public void setSafeGold(long safeGold) {
    this.safeGold = safeGold;
  }

  public List<PlayerPrizePool> getPrizePools() {
    return prizePools;
  }

  public void setPrizePools(List<PlayerPrizePool> prizePools) {
    this.prizePools = prizePools;
  }

}
