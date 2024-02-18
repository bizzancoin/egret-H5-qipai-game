package com.idealighter.game.service.common.sto;

import java.util.List;
import lombok.Data;

@Data
public class AllRoomListSto {
  private Integer gameId;
  private String gameName;
  private List<RoomListSto> rooms;
}
