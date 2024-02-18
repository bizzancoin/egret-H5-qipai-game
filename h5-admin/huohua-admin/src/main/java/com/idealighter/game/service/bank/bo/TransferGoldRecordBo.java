package com.idealighter.game.service.bank.bo;

import java.util.Date;

import lombok.Data;

@Data
public class TransferGoldRecordBo {
  private Integer id;

  /**
   * 1转出 2转入.
   */
  private Byte type;

  /**
   * 对方类型（0普通用户1代理）.
   */
  private Byte oppositeType;

  /**
   * 玩家id.
   */
  private Long playerId;

  /**
   * 玩家靓号.
   */
  private Long playerSuperId;

  /**
   * 玩家昵称.
   */
  private String playerName;

  /**
   * 对方玩家Id.
   */
  private Long oppositePlayerId;

  /**
   * 对方靓号Id.
   */
  private Long oppositeSuperId;

  /**
   * 对方玩家昵称.
   */
  private String oppositePlayerName;

  /**
   * 发送前银行金币.
   */
  private Long beforeSafeGold;

  /**
   * 发送后银行金币.
   */
  private Long afterSafeGold;

  /**
   * 手续费.
   */
  private Long fee;

  /**
   * 费率.
   */
  private Integer feeRate;

  /**
   * 变化的银行金币.
   */
  private Long changeSafeGold;

  /**
   * 创建时间.
   */
  private Date createTime;
  
  private Byte playerType;
}
