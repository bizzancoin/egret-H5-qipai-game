
package com.idealighter.game.games.bairenniuniu.message;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 玩家申请庄家列表 .
 *
 */
@ResMsg(ModuleMsgIdConstant.BaiRenNiuNiu.RES_APPLY_BANKER)
@Data
public class ResApplyBankersMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.BaiRenNiuNiu.RES_APPLY_BANKER;
  // 申请人
  @Protobuf(order = 2, fieldType = FieldType.INT64)
  private List<Long> applicants = new ArrayList<>();
}
