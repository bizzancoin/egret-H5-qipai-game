package com.idealighter.game.core.service.games.common;

import com.idealighter.game.gamehall.dto.SeatInfo;

import lombok.Data;

/**
 * 座位.
 *
 */
@Data
public abstract class AbstractSeat {

  // 玩家id
  protected long playerId;
  // 玩家昵称
  protected String playerName;
  // 玩家性别,0:男,非0:女
  protected byte sex = 0;
  // 座位顺序(从0开始),没特殊需求最好不该变它
  protected int order;
  // 该位置是否是机器人
  protected boolean robot;
  // 玩家拥有的合计筹码
  protected long totalChips = 0;
  // 状态(1:入座,2:准备,3:游戏中)
  protected SeatState state;

  /**
   * 是否已经准备过.
   * 
   * @return 是否准备.
   */
  public boolean readied() {
    return state != null && state.val >= SeatState.READY.val;
  }

  /**
   * 座位.
   * 
   * @param order .
   */
  public AbstractSeat(int order) {
    this.order = order;
  }

  /**
   * 获取用于传输的SeatInfo.
   * 
   * @return 座位.
   */
  public SeatInfo seatInfo() {
    SeatInfo seatInfo = new SeatInfo();
    seatInfo.setTableId(table().id);
    seatInfo.setOrder(order);
    seatInfo.setPlayerId(playerId);

    return seatInfo;
  }

  /**
   * 房间所属的桌子.
   * 
   * @return 桌子.
   */
  public abstract AbstractTable table();

}
