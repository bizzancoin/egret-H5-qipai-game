package com.idealighter.game.service.player.bo;

import lombok.Data;

@Data
public class PlayerBo {
  /**
   * 玩家id.
   */
  private Long id;

  /**
   * 靓号id.
   */
  private Long superId;

  /**
   * 用户名.
   */
  private String userName;

  /**
   * 玩家昵称.
   */
  private String playerName;
  
  /**
   * 手机号.
   */
  private String phone;
  
  
  /**
   * 金币.
   */
  private Long gold;

  /**
   * 保险箱金币.
   */
  private Long safeGold;
  
  private String channelId;

}
