package com.idealighter.game.app.recharge.dto;

import java.util.Date;

import lombok.Data;

@Data
public class RechargeDto {
  private int id;
  /**
   * 订单号（本地）.
   */
  private String tradeNo;

  /**
   * 外部交易号(微信/支付的订单编号).
   */
  private String outsideNo;

  /**
   * 支付类型(1支付宝2微信).
   */
  private Byte payType;

  /**
   * 玩家id.
   */
  private Long playerId;

  /**
   * 充值itemId.
   */
  private Integer rechargeItemId;

  /**
   * 金币.
   */
  private Long gold;

  /**
   * 赠送金币.
   */
  private Long sendGold;

  /**
   * 价格（分）.
   */
  private Integer price;

  /**
   * 创建时间.
   */
  private Date createTime;

  /**
   * 支付时间.
   */
  private Date payTime;

  /**
   * 订单状态（0待支付，1支付到账）.
   */
  private Byte state;
}
