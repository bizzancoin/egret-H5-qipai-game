
package com.idealighter.game.welfare.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.welfare.dto.WelfareInfo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 福利刷新.
 *
 */
@ResMsg(ModuleMsgIdConstant.Welfare.NOTICE_REFRESH_WELFARE)
@Data
public class ResRefreshWelfareMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Welfare.NOTICE_REFRESH_WELFARE;
  // 福利信息
  @Protobuf(order = 2)
  private List<WelfareInfo> welfares = new ArrayList<>();
}
