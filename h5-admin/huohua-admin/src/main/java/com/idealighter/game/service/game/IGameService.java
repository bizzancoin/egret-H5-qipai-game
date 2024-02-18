package com.idealighter.game.service.game;

import com.idealighter.game.app.game.dto.IdName;
import com.idealighter.game.service.game.bo.GameBo;
import com.idealighter.game.service.robotcfg.bo.RoomConfigBo;

import java.util.List;

public interface IGameService {
  List<GameBo> findGame(Boolean active);

  void changeState(int gameId, boolean isActive);

  List<IdName> findRoom(Integer gameId, Byte active);
  
  List<RoomConfigBo> findRoomConfig(Integer gameId, Byte active);

  IdName findRoomById(Integer gameId, Integer roomId);
}
