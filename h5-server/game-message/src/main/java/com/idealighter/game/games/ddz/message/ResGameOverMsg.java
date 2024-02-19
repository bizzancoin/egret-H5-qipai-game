
package com.idealighter.game.games.ddz.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.games.ddz.dto.BillInfo;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * 游戏结束(后面会有结算等信息) .
 *
 */
@ResMsg(ModuleMsgIdConstant.DDZ.RES_GAME_OVER)
@Data
public class ResGameOverMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.RES_GAME_OVER;
  @Protobuf(order = 2)
  private int stageTime;
  @Protobuf(order = 3)
  private int spring; // 春天(-1:反春天,0:普通,1:春天)
  // 结算信息
  @Protobuf(order = 4)
  private List<BillInfo> bills = new ArrayList<>();
}
