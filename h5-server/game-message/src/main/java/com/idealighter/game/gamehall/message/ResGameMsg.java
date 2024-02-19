package com.idealighter.game.gamehall.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.gamehall.dto.GameDto;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import java.util.List;

import lombok.Data;

@ResMsg(ModuleMsgIdConstant.Game.GAME_LIST)
@Data
public class ResGameMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Game.GAME_LIST;

  @Protobuf(order = 2)
  private List<GameDto> games;
}
