
package com.idealighter.game.notice.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

/**
 * 跑马灯公告.
 *
 */
@ResMsg(ModuleMsgIdConstant.Notice.NOTICE_MARQUEE_NOTICE)
@Data
public class ResMarqueeNoticeMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Notice.NOTICE_MARQUEE_NOTICE;
  // 内容
  @Protobuf(order = 2)
  private String content;
  // 0:游戏公告,1:官方公告
  @Protobuf(order = 3)
  private int type;

  @Protobuf(order = 4)
  private int interval;

  @Protobuf(order = 5)
  private int times;

  @Protobuf(order = 6)
  private String color;
}
