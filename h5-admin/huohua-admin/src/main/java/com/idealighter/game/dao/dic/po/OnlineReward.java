package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class OnlineReward implements Serializable {
  /**
   * id
   */
  private Integer id;

  /**
   * 游戏
   */
  private Integer game;

  /**
   * 房间
   */
  private Integer room;

  /**
   * vip是否赠送
   */
  private Boolean vip;

  /**
   * 高于10级的玩家是否赠送
   */
  private Boolean tenlevelupper;

  /**
   * 下雨等于10级的玩家是否赠送
   */
  private Boolean tenlevellower;

  /**
   * 桌子在线赠送金币
   */
  private Integer tablegold;

  /**
   * 桌子在线赠送金币时长(分钟)
   */
  private Integer tableduration;

  /**
   * 房间在线赠送金币
   */
  private Integer roomgold;

  /**
   * 房间在线赠送金币时长(分钟)
   */
  private Integer roomduration;

  /**
   * 桌子配置生效
   */
  private Boolean tableeffective;

  /**
   * 房间配置生效
   */
  private Boolean roomeffective;

  private static final long serialVersionUID = 1L;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getGame() {
    return game;
  }

  public void setGame(Integer game) {
    this.game = game;
  }

  public Integer getRoom() {
    return room;
  }

  public void setRoom(Integer room) {
    this.room = room;
  }

  public Boolean getVip() {
    return vip;
  }

  public void setVip(Boolean vip) {
    this.vip = vip;
  }

  public Boolean getTenlevelupper() {
    return tenlevelupper;
  }

  public void setTenlevelupper(Boolean tenlevelupper) {
    this.tenlevelupper = tenlevelupper;
  }

  public Boolean getTenlevellower() {
    return tenlevellower;
  }

  public void setTenlevellower(Boolean tenlevellower) {
    this.tenlevellower = tenlevellower;
  }

  public Integer getTablegold() {
    return tablegold;
  }

  public void setTablegold(Integer tablegold) {
    this.tablegold = tablegold;
  }

  public Integer getTableduration() {
    return tableduration;
  }

  public void setTableduration(Integer tableduration) {
    this.tableduration = tableduration;
  }

  public Integer getRoomgold() {
    return roomgold;
  }

  public void setRoomgold(Integer roomgold) {
    this.roomgold = roomgold;
  }

  public Integer getRoomduration() {
    return roomduration;
  }

  public void setRoomduration(Integer roomduration) {
    this.roomduration = roomduration;
  }

  public Boolean getTableeffective() {
    return tableeffective;
  }

  public void setTableeffective(Boolean tableeffective) {
    this.tableeffective = tableeffective;
  }

  public Boolean getRoomeffective() {
    return roomeffective;
  }

  public void setRoomeffective(Boolean roomeffective) {
    this.roomeffective = roomeffective;
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
    OnlineReward other = (OnlineReward) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getGame() == null ? other.getGame() == null
            : this.getGame().equals(other.getGame()))
        && (this.getRoom() == null ? other.getRoom() == null
            : this.getRoom().equals(other.getRoom()))
        && (this.getVip() == null ? other.getVip() == null : this.getVip().equals(other.getVip()))
        && (this.getTenlevelupper() == null ? other.getTenlevelupper() == null
            : this.getTenlevelupper().equals(other.getTenlevelupper()))
        && (this.getTenlevellower() == null ? other.getTenlevellower() == null
            : this.getTenlevellower().equals(other.getTenlevellower()))
        && (this.getTablegold() == null ? other.getTablegold() == null
            : this.getTablegold().equals(other.getTablegold()))
        && (this.getTableduration() == null ? other.getTableduration() == null
            : this.getTableduration().equals(other.getTableduration()))
        && (this.getRoomgold() == null ? other.getRoomgold() == null
            : this.getRoomgold().equals(other.getRoomgold()))
        && (this.getRoomduration() == null ? other.getRoomduration() == null
            : this.getRoomduration().equals(other.getRoomduration()))
        && (this.getTableeffective() == null ? other.getTableeffective() == null
            : this.getTableeffective().equals(other.getTableeffective()))
        && (this.getRoomeffective() == null ? other.getRoomeffective() == null
            : this.getRoomeffective().equals(other.getRoomeffective()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getGame() == null) ? 0 : getGame().hashCode());
    result = prime * result + ((getRoom() == null) ? 0 : getRoom().hashCode());
    result = prime * result + ((getVip() == null) ? 0 : getVip().hashCode());
    result = prime * result + ((getTenlevelupper() == null) ? 0 : getTenlevelupper().hashCode());
    result = prime * result + ((getTenlevellower() == null) ? 0 : getTenlevellower().hashCode());
    result = prime * result + ((getTablegold() == null) ? 0 : getTablegold().hashCode());
    result = prime * result + ((getTableduration() == null) ? 0 : getTableduration().hashCode());
    result = prime * result + ((getRoomgold() == null) ? 0 : getRoomgold().hashCode());
    result = prime * result + ((getRoomduration() == null) ? 0 : getRoomduration().hashCode());
    result = prime * result + ((getTableeffective() == null) ? 0 : getTableeffective().hashCode());
    result = prime * result + ((getRoomeffective() == null) ? 0 : getRoomeffective().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", game=").append(game);
    sb.append(", room=").append(room);
    sb.append(", vip=").append(vip);
    sb.append(", tenlevelupper=").append(tenlevelupper);
    sb.append(", tenlevellower=").append(tenlevellower);
    sb.append(", tablegold=").append(tablegold);
    sb.append(", tableduration=").append(tableduration);
    sb.append(", roomgold=").append(roomgold);
    sb.append(", roomduration=").append(roomduration);
    sb.append(", tableeffective=").append(tableeffective);
    sb.append(", roomeffective=").append(roomeffective);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
