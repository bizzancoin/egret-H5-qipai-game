package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class SharkBonusIconxy implements Serializable {
  /**
   * 格子ID
   */
  private Integer id;

  /**
   * 图标ID
   */
  private Integer iconid;

  /**
   * X左边
   */
  private Integer posx;

  /**
   * Y坐标
   */
  private Integer posy;

  private static final long serialVersionUID = 1L;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getIconid() {
    return iconid;
  }

  public void setIconid(Integer iconid) {
    this.iconid = iconid;
  }

  public Integer getPosx() {
    return posx;
  }

  public void setPosx(Integer posx) {
    this.posx = posx;
  }

  public Integer getPosy() {
    return posy;
  }

  public void setPosy(Integer posy) {
    this.posy = posy;
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
    SharkBonusIconxy other = (SharkBonusIconxy) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getIconid() == null ? other.getIconid() == null
            : this.getIconid().equals(other.getIconid()))
        && (this.getPosx() == null ? other.getPosx() == null
            : this.getPosx().equals(other.getPosx()))
        && (this.getPosy() == null ? other.getPosy() == null
            : this.getPosy().equals(other.getPosy()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getIconid() == null) ? 0 : getIconid().hashCode());
    result = prime * result + ((getPosx() == null) ? 0 : getPosx().hashCode());
    result = prime * result + ((getPosy() == null) ? 0 : getPosy().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", iconid=").append(iconid);
    sb.append(", posx=").append(posx);
    sb.append(", posy=").append(posy);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
