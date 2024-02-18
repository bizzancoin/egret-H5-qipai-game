package com.idealighter.game.dao.data.po;

import java.io.Serializable;

/**
 * @author
 */
public class RoomPrizePoolGroupKey implements Serializable {
  private Integer game;

  private Integer roomId;

  private static final long serialVersionUID = 1L;

  public Integer getGame() {
    return game;
  }

  public void setGame(Integer game) {
    this.game = game;
  }

  public Integer getRoomId() {
    return roomId;
  }

  public void setRoomId(Integer roomId) {
    this.roomId = roomId;
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (that == null) {
      return false;
    }
    if (getClass() != that.getClass()) {
      return false;
    }
    RoomPrizePoolGroupKey other = (RoomPrizePoolGroupKey) that;
    return (this.getGame() == null ? other.getGame() == null
        : this.getGame().equals(other.getGame()))
        && (this.getRoomId() == null ? other.getRoomId() == null
            : this.getRoomId().equals(other.getRoomId()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getGame() == null) ? 0 : getGame().hashCode());
    result = prime * result + ((getRoomId() == null) ? 0 : getRoomId().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", game=").append(game);
    sb.append(", roomId=").append(roomId);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
