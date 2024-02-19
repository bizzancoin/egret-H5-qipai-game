
package com.idealighter.game.games.jcby.message;

import java.util.ArrayList;
import java.util.List;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.games.jcby.dto.GameInfo;
import com.idealighter.game.games.jcby.dto.RoomInfo;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

/**
 * 进入游戏大厅返回房间数据 .
 *
 */
@ResMsg(ModuleMsgIdConstant.Jcby.GAME_INFO)
@Data
public class ResGameInfoMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Jcby.GAME_INFO;
  // 当前房间信息
  @Protobuf(order = 2)
  private RoomInfo room;
  // 当前房间成员信息
  @Protobuf(order = 3)
  private List<com.idealighter.game.gamehall.dto.MemInfo> mems = new ArrayList<>();
  @Protobuf(order = 4)
  private GameInfo gameInfo;
}
