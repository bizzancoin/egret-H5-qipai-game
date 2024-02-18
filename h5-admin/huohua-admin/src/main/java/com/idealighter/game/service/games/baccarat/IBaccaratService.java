package com.idealighter.game.service.games.baccarat;

import com.idealighter.game.common.ResultPage;
import com.idealighter.game.service.game.bo.RoomTypeBo;
import com.idealighter.game.service.games.baccarat.bo.BaccaratRoomBo;

import java.util.List;

public interface IBaccaratService {
  ResultPage<RoomTypeBo> findRoomType();

  ResultPage<BaccaratRoomBo> findRoomByPage(Integer page, Integer pageSize);

  void saveRoom(BaccaratRoomBo bo);

  void changeStatus(int roomId, byte isActive);

  void deleteForCloseRoom(int id);

  List<BaccaratRoomBo> findRoom(Byte active);

  BaccaratRoomBo findRoomById(Integer roomId);
}
