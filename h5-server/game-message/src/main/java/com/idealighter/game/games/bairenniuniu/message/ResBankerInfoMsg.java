
package com.idealighter.game.games.bairenniuniu.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

/**
 * 庄家信息 .
 *
 */
@ResMsg(ModuleMsgIdConstant.BaiRenNiuNiu.RES_BANKER_INFO)
@Data
public class ResBankerInfoMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.BaiRenNiuNiu.RES_BANKER_INFO;
  // 庄家id
  @Protobuf(order = 2)
  private long playerId;
  // 昵称
  @Protobuf(order = 3)
  private String name;
  // 性别 0男1女3系统
  @Protobuf(order = 4)
  private int sex;
  // 筹码
  @Protobuf(order = 5)
  private long chips;
  // 局数
  @Protobuf(order = 6)
  private int num;
  // 成绩
  @Protobuf(order = 7)
  private long score;
}
