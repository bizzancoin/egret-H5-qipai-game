package com.idealighter.game.core.service.games.common;

import lombok.Data;

@Data
public abstract class AbstractBaseRoom {

  protected final int roomId;

  protected int serial = 0;

  protected AbstractBaseRoom(int roomId) {
    this.roomId = roomId;

  }
}
