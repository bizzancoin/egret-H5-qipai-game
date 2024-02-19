
package com.idealighter.game.dictionary.domain;

/**
 * @author exccel .-generator.
 *
 */
public class GameTypeDomain {

  // id
  private int id;
  // typename
  private String name;
  // 索引位置
  private int index;
  // 所属游戏编号
  private String games;

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

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public String getGames() {
    return games;
  }

  public void setGames(String games) {
    this.games = games;
  }

}
