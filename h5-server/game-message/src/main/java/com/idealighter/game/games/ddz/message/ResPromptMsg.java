
package com.idealighter.game.games.ddz.message;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * 玩家请求提示结果 .
 *
 */
@ResMsg(ModuleMsgIdConstant.DDZ.RES_PROMPT)
@Data
public class ResPromptMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.RES_PROMPT;
  // 提示的牌(牌的数量为空则没有大过上家的牌)
  @Protobuf(fieldType = FieldType.INT32, order = 2)
  private List<Integer> cards = new ArrayList<>();
}
