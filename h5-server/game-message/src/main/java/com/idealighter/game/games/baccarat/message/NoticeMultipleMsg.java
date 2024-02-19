
package com.idealighter.game.games.baccarat.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.games.baccarat.dto.IconMultiple;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 返回倍率结果 .
 *
 */
@ResMsg(ModuleMsgIdConstant.Baccarat.NOTICE_MULTIPLE)
@Data
public class NoticeMultipleMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Baccarat.NOTICE_MULTIPLE;
  // 本局所开出的倍率
  @Protobuf(order = 2)
  private List<IconMultiple> iconMulti = new ArrayList<>();
}
