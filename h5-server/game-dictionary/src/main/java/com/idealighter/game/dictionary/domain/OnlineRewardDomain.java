
package com.idealighter.game.dictionary.domain;


public class OnlineRewardDomain {

  // id
  private int id;
  // 游戏
  private int game;
  // 房间
  private int room;
  // vip是否赠送
  private boolean vip;
  // 高于10级的玩家是否赠送
  private boolean tenLevelUpper;
  // 下雨等于10级的玩家是否赠送
  private boolean tenLevelLower;
  // 桌子在线赠送金币
  private int tableGold;
  // 桌子在线赠送金币时长(分钟)
  private int tableDuration;
  // 房间在线赠送金币
  private int roomGold;
  // 房间在线赠送金币时长(分钟)
  private int roomDuration;
  // 桌子配置生效
  private boolean tableEffective;
  // 房间配置生效
  private boolean roomEffective;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getGame() {
    return game;
  }

  public void setGame(int game) {
    this.game = game;
  }

  public int getRoom() {
    return room;
  }

  public void setRoom(int room) {
    this.room = room;
  }

  public boolean getVip() {
    return vip;
  }

  public void setVip(boolean vip) {
    this.vip = vip;
  }

  public boolean getTenLevelUpper() {
    return tenLevelUpper;
  }

  public void setTenLevelUpper(boolean tenLevelUpper) {
    this.tenLevelUpper = tenLevelUpper;
  }

  public boolean getTenLevelLower() {
    return tenLevelLower;
  }

  public void setTenLevelLower(boolean tenLevelLower) {
    this.tenLevelLower = tenLevelLower;
  }

  public int getTableGold() {
    return tableGold;
  }

  public void setTableGold(int tableGold) {
    this.tableGold = tableGold;
  }

  public int getTableDuration() {
    return tableDuration;
  }

  public void setTableDuration(int tableDuration) {
    this.tableDuration = tableDuration;
  }

  public int getRoomGold() {
    return roomGold;
  }

  public void setRoomGold(int roomGold) {
    this.roomGold = roomGold;
  }

  public int getRoomDuration() {
    return roomDuration;
  }

  public void setRoomDuration(int roomDuration) {
    this.roomDuration = roomDuration;
  }

  public boolean getTableEffective() {
    return tableEffective;
  }

  public void setTableEffective(boolean tableEffective) {
    this.tableEffective = tableEffective;
  }

  public boolean getRoomEffective() {
    return roomEffective;
  }

  public void setRoomEffective(boolean roomEffective) {
    this.roomEffective = roomEffective;
  }

}
