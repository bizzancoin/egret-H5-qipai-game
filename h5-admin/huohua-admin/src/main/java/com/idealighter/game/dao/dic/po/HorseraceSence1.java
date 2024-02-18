package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class HorseraceSence1 implements Serializable {
  /**
   * id
   */
  private Integer id;

  /**
   * 修饰物id
   */
  private Integer itemid;

  /**
   * 横坐标
   */
  private Integer px;

  /**
   * 纵坐标
   */
  private Integer py;

  private static final long serialVersionUID = 1L;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getItemid() {
    return itemid;
  }

  public void setItemid(Integer itemid) {
    this.itemid = itemid;
  }

  public Integer getPx() {
    return px;
  }

  public void setPx(Integer px) {
    this.px = px;
  }

  public Integer getPy() {
    return py;
  }

  public void setPy(Integer py) {
    this.py = py;
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
    HorseraceSence1 other = (HorseraceSence1) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getItemid() == null ? other.getItemid() == null
            : this.getItemid().equals(other.getItemid()))
        && (this.getPx() == null ? other.getPx() == null : this.getPx().equals(other.getPx()))
        && (this.getPy() == null ? other.getPy() == null : this.getPy().equals(other.getPy()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getItemid() == null) ? 0 : getItemid().hashCode());
    result = prime * result + ((getPx() == null) ? 0 : getPx().hashCode());
    result = prime * result + ((getPy() == null) ? 0 : getPy().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", itemid=").append(itemid);
    sb.append(", px=").append(px);
    sb.append(", py=").append(py);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
