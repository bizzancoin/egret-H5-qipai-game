package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class GameType implements Serializable {
  /**
   * id
   */
  private Integer id;

  /**
   * typename
   */
  private String name;

  /**
   * 索引位置
   */
  private Integer index;

  /**
   * 所属游戏编号
   */
  private String games;

  private static final long serialVersionUID = 1L;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getIndex() {
    return index;
  }

  public void setIndex(Integer index) {
    this.index = index;
  }

  public String getGames() {
    return games;
  }

  public void setGames(String games) {
    this.games = games;
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
    GameType other = (GameType) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getName() == null ? other.getName() == null
            : this.getName().equals(other.getName()))
        && (this.getIndex() == null ? other.getIndex() == null
            : this.getIndex().equals(other.getIndex()))
        && (this.getGames() == null ? other.getGames() == null
            : this.getGames().equals(other.getGames()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
    result = prime * result + ((getIndex() == null) ? 0 : getIndex().hashCode());
    result = prime * result + ((getGames() == null) ? 0 : getGames().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", name=").append(name);
    sb.append(", index=").append(index);
    sb.append(", games=").append(games);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
