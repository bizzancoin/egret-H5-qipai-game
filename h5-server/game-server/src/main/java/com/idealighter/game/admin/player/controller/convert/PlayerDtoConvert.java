package com.idealighter.game.admin.player.controller.convert;

import com.idealighter.game.admin.player.controller.dto.OnLinePlayerDto;
import com.idealighter.game.core.constant.PlayerType;
import com.idealighter.game.core.constant.PlayerTypeForClient;
import com.idealighter.game.core.constant.RegisterType;
import com.idealighter.game.core.convert.BooleanStateConvert;
import com.idealighter.game.core.service.player.struct.Player;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = BooleanStateConvert.class)
public interface PlayerDtoConvert {
  PlayerDtoConvert INSTANCE = Mappers.getMapper(PlayerDtoConvert.class);

  /**
   * 获取用户类型.
   * 
   * @param player 玩家.
   * @return 玩家类型.
   */
  default Byte toPlayerType(Player player) {
    Byte playerType = PlayerTypeForClient.USER;
    if (player.getRegisterType() == RegisterType.ROBOT) {
      playerType = PlayerTypeForClient.ROBOT;
    }
    if (player.getType() == PlayerType.AGENT) {
      playerType = PlayerTypeForClient.AGENT;
    }
    return playerType;
  }

  @Mapping(target = "playerId", source = "id")
  @Mapping(target = "nickName", source = "playerName")
  @Mapping(target = "onlineState", source = "online")
  @Mapping(target = "state", expression = "java(player.isLocked()?(byte)0:(byte)1)")
  @Mapping(target = "totalGold", expression = "java(player.getGold()+player.getSafeGold())")
  @Mapping(target = "playerStatus",
      expression = "java(player.curRoom == null ? \"游戏大厅\" : player.curRoom.game().getDesc() "
          + "+ \"-\" + player.curRoom.getName())")
  @Mapping(target = "playerType", expression = "java(toPlayerType(player))")
  OnLinePlayerDto toOnLinePlayerDto(Player player);

}
