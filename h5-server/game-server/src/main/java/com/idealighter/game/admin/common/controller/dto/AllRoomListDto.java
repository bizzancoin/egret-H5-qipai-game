package com.idealighter.game.admin.common.controller.dto;

import java.util.List;

import lombok.Data;

@Data
public class AllRoomListDto {
  private Integer gameId;
  private String gameName;
  private List<RoomListDto> rooms;
}
