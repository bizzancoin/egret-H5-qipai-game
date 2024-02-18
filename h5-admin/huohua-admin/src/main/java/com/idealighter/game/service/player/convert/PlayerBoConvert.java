package com.idealighter.game.service.player.convert;

import com.idealighter.game.dao.data.po.PlayerMain;
import com.idealighter.game.dao.data.po.PlayerStatusRecord;
import com.idealighter.game.service.player.bo.PlayerBo;
import com.idealighter.game.service.player.bo.PlayerListBo;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PlayerBoConvert {
  PlayerBo toPlayerBo(PlayerMain playerMain);

  List<PlayerBo> toPlayerBos(List<PlayerMain> players);

  @Mapping(target = "id", source = "playerMain.id")
  PlayerListBo toPlayerListBo(PlayerMain playerMain, PlayerStatusRecord statusRecord);

}
