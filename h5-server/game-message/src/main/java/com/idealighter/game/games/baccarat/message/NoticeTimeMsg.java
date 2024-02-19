
package com.idealighter.game.games.baccarat.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

/**
 * 通知客户端计时 .
 *
 */
@ResMsg(ModuleMsgIdConstant.Baccarat.NOTICE_TIME)
@Data
public class NoticeTimeMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Baccarat.NOTICE_TIME;
  // 告知客户端游戏进行到的阶段
  @Protobuf(order = 2)
  private int state;
  // 计时时间(单位秒)
  @Protobuf(order = 3)
  private int time;
}
