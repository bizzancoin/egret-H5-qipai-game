
package com.idealighter.game.games.ddz.message;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import lombok.Data;

/**
 * 玩家加倍结果 .
 *
 */
@ResMsg(ModuleMsgIdConstant.DDZ.RES_DOUBLE)
@Data
public class ResDoubleMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.RES_DOUBLE;
  // 加倍的玩家
  @Protobuf(order = 2)
  private long playerId;
  // 加倍的玩家order
  @Protobuf(order = 3, fieldType = FieldType.INT32)
  private int order;
  // 0:不加倍,非0:加倍
  @Protobuf(order = 4, fieldType = FieldType.INT32)
  private int doubled;
}
