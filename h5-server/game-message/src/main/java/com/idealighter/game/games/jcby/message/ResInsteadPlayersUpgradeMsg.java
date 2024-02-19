
package com.idealighter.game.games.jcby.message;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 代发碰撞玩家列表更新 .
 *
 */
@ResMsg(ModuleMsgIdConstant.Jcby.RES_INSTEAD_PLAYER_UPGRADE)
@Data
public class ResInsteadPlayersUpgradeMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Jcby.RES_INSTEAD_PLAYER_UPGRADE;
  // 代发碰撞玩家列表
  @Protobuf(order = 2, fieldType = FieldType.INT64)
  private List<Long> insteadPlayers = new ArrayList<>();
}
