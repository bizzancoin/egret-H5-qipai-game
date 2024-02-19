
package com.idealighter.game.player.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

/**
 * 玩家属性改变消息.
 *
 */
@ResMsg(ModuleMsgIdConstant.Player.NOTICE_ATTR_CHANGE)
@Data
public class ResPlayerAttrChangeMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Player.NOTICE_ATTR_CHANGE;
  // 属性类型(1:元宝，2:金币,3:保险箱金币,4:积分,5:奖券,6:玩家等级,7:vip等级,8:vip时长,9:经验)
  @Protobuf(order = 2)
  private int type;
  // 属性值
  @Protobuf(order = 3)
  private long val;
}
