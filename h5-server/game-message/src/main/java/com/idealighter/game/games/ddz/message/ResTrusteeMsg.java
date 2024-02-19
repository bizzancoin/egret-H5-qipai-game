
package com.idealighter.game.games.ddz.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import lombok.Data;

/**
 * 玩家喊话 .
 *
 */
@ResMsg(ModuleMsgIdConstant.DDZ.RES_TRESTEE)
@Data
public class ResTrusteeMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.RES_TRESTEE;
  @Protobuf(order = 2)
  private int order;
  @Protobuf(order = 3)
  private long playerId;
  @Protobuf(order = 4)
  private int trustee; // 0:取消托管,1:托管
}
