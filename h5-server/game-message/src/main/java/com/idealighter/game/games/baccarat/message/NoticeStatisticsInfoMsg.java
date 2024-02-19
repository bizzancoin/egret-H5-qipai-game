
package com.idealighter.game.games.baccarat.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

/**
 * 结果统计信息 .
 *
 */
@ResMsg(ModuleMsgIdConstant.Baccarat.NOTICE_STATISTICS_INFO)
@Data
public class NoticeStatisticsInfoMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Baccarat.NOTICE_STATISTICS_INFO;
  // 庄赢
  @Protobuf(order = 2)
  private int bankerWin;
  // 闲赢
  @Protobuf(order = 3)
  private int playerWin;
  // 和
  @Protobuf(order = 4)
  private int tie;
  // 庄对
  @Protobuf(order = 5)
  private int bankerPaire;
  // 闲对
  @Protobuf(order = 6)
  private int playerPaire;
  // 龙
  @Protobuf(order = 7)
  private int dragon;
  // 虎
  @Protobuf(order = 8)
  private int tiger;
  // 龙虎和
  @Protobuf(order = 9)
  private int dragonTigerTie;
  // 总局数
  @Protobuf(order = 10)
  private int score;
}
