package com.idealighter.game.app.player.convert;

import com.idealighter.game.app.player.dto.PlayerDto;
import com.idealighter.game.common.constant.PlayerType;
import com.idealighter.game.common.constant.PlayerTypeForClient;
import com.idealighter.game.common.constant.RegisterType;
import com.idealighter.game.common.convert.BooleanStateConvert;
import com.idealighter.game.service.player.bo.PlayerListBo;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(uses = BooleanStateConvert.class)
public interface PlayerDtoConvert {

  /**
   * 玩家获取玩家类别.
   * 
   * @param bo 玩家.
   * @return 类别.
   */
  default Byte toPlayerType(PlayerListBo bo) {
    Byte playerType = PlayerTypeForClient.USER;
    if (bo.getRegisterType().byteValue() == RegisterType.ROBOT) {
      playerType = PlayerTypeForClient.ROBOT;
    }
    if (bo.getType().byteValue() == PlayerType.AGENT) {
      playerType = PlayerTypeForClient.AGENT;
    }
    return playerType;
  }

  @Mapping(target = "playerId", source = "id")
  @Mapping(target = "nickName", source = "playerName")
  @Mapping(target = "onlineState", source = "online")
  @Mapping(target = "state", expression = "java(bo.getLocked()?(byte)0:(byte)1)")
  @Mapping(target = "totalGold", expression = "java(bo.getGold()+bo.getSafeGold())")
  @Mapping(target = "playerType", expression = "java(toPlayerType(bo))")
  PlayerDto toUserDto(PlayerListBo bo);

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<PlayerDto> toUserDtos(List<PlayerListBo> bos);
}
