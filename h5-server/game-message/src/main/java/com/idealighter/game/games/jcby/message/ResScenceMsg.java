
package com.idealighter.game.games.jcby.message;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.games.jcby.dto.BatteryInfo;
import com.idealighter.game.games.jcby.dto.FishInfo;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 玩家请求场景数据结果 .
 *
 */
@ResMsg(ModuleMsgIdConstant.Jcby.SCREEN)
@Data
public class ResScenceMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Jcby.SCREEN;
  // 场景id
  @Protobuf(order = 2)
  private int scenceId;
  // 鱼
  @Protobuf(order = 3)
  private List<FishInfo> fishs = new ArrayList<>();
  // 炮台
  @Protobuf(order = 4)
  private List<BatteryInfo> batterys = new ArrayList<>();
  // 代发碰撞玩家列表
  @Protobuf(order = 5, fieldType = FieldType.INT64)
  private List<Long> insteadPlayers = new ArrayList<>();
}
