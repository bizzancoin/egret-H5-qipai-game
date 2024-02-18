package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class HorseraceItem implements Serializable {
  /**
   * id
   */
  private Integer id;

  /**
   * 资源类型
   */
  private Integer type;

  /**
   * 资源存放路径
   */
  private String imagename;

  private static final long serialVersionUID = 1L;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getImagename() {
    return imagename;
  }

  public void setImagename(String imagename) {
    this.imagename = imagename;
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
    HorseraceItem other = (HorseraceItem) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getType() == null ? other.getType() == null
            : this.getType().equals(other.getType()))
        && (this.getImagename() == null ? other.getImagename() == null
            : this.getImagename().equals(other.getImagename()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
    result = prime * result + ((getImagename() == null) ? 0 : getImagename().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", type=").append(type);
    sb.append(", imagename=").append(imagename);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
