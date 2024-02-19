
package com.idealighter.game.games.bairenniuniu.dto;

import lombok.Data;

/**
 * 百人牛牛结算信息 .
 *
 */
@Data
public class BillInfo {

  // 座位顺序
  private int order;

  // 结算筹码
  private long chips;
}
