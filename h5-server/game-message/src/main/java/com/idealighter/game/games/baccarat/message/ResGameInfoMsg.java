package com.idealighter.game.games.baccarat.message;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.gamehall.dto.MemInfo;
import com.idealighter.game.games.baccarat.dto.RoomInfo;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import java.util.List;

import lombok.Data;

@ResMsg(ModuleMsgIdConstant.Baccarat.GAME_INFO)
@Data
public class ResGameInfoMsg implements ResMessage {

  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Baccarat.GAME_INFO;

  @Protobuf(order = 2)
  private RoomInfo roomInfo;

  @Protobuf(order = 3)
  private List<MemInfo> mems;

  @Protobuf(order = 4, fieldType = FieldType.INT64)
  private List<Long> betInfos;
  
  @Protobuf(order = 5)
  private int cardIndex = 0;
}
