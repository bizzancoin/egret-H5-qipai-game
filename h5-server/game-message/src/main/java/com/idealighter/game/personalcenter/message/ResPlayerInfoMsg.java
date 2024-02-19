package com.idealighter.game.personalcenter.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.login.dto.GameInfoDto;
import com.idealighter.game.login.dto.PlayerInfoDto;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

/**
 * 玩家信息. .
 * 
 * @author . messageGenerator
 *
 */
@ResMsg(ModuleMsgIdConstant.Player.NOTICE_PLAYER_INFO)
@Data
public class ResPlayerInfoMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Player.NOTICE_PLAYER_INFO;
  // 玩家信息
  @Protobuf(order = 2)
  private PlayerInfoDto playerInfo;

  // 游戏信息
  @Protobuf(order = 3)
  private GameInfoDto gameInfo;

}
