package com.idealighter.game.games.ddz.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;
import lombok.Data;

@ReqMsg(ModuleMsgIdConstant.DDZ.FAST_ENTER_TABLE)
@Data
public class ReqFastEnterTableMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.FAST_ENTER_TABLE;
  // 房间id
  @Protobuf(order = 2)
  private int roomId;
}
