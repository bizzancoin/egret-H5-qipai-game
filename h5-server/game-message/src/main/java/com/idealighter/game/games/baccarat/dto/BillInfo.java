
package com.idealighter.game.games.baccarat.dto;

import lombok.Data;

/**
 * 百家乐结算信息 .
 *
 */
@Data
public class BillInfo {

  // 座位顺序
  private int order;

  // 结算筹码
  private long chips;

}
