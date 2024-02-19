package com.idealighter.game.dictionary.domain;

/**
 * @author exccel .-generator.
 *
 */
public class DdzRoomTypeDomain {

  // id
  private int id;
  // 房间状态（0空闲，1普通，2拥挤，3爆满
  private String name;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
