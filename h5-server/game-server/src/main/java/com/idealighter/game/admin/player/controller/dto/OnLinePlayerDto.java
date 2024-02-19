
package com.idealighter.game.admin.player.controller.dto;

import java.util.Date;

import lombok.Data;

/**
 * 在线玩家 .
 * 
 * @date 2015年12月1日 下午6:08:18
 *
 */
@Data
public class OnLinePlayerDto {

  private long playerId;
  private long superId;
  private String userName;
  private String nickName;
  private String phone;
  private int level;
  private long gold;
  private long safeGold;
  private long totalGold;
  private Date loginTime;
  private String loginIp;
  private String playerStatus;
  private byte state;
  private byte onlineState;
  private byte playerType;

  private String channelId;
  // 手机在线
  // private boolean fromPhone;

  /**
   * @param player 玩家.
   */
  // public OnLinePlayerDto(Player player) {
  // super();
  // this.playerId = player.getId();
  // this.superId = player.getSuperId();
  // this.userName = player.getUserName();
  // this.nickName = player.getPlayerName();
  // this.phone = player.getPhone();
  // this.level = player.getLevel();
  // this.gold = player.getGold();
  // this.safeGold = player.getSafeGold();
  // this.totalGold = player.getGold() +player.getSafeGold();
  // this.loginTime = player.getLoginTime();
  // this.loginIp = player.ip;
  // this.state = player.
  // this.playerStatus =
  // player.curRoom == null ? "游戏大厅" : player.curRoom.game().desc + "-" + player.curRoom.name;
  // this.fromPhone = player.fromPhone;
  // }
}
