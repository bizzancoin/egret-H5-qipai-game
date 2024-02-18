package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class HorseraceSenceType implements Serializable {
  /**
   * 场景id
   */
  private Integer id;

  /**
   * 资源路径
   */
  private String bgname;

  /**
   * 道具种类id
   */
  private Integer senceid;

  /**
   * 中间的张数
   */
  private Integer middle;

  private static final long serialVersionUID = 1L;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getBgname() {
    return bgname;
  }

  public void setBgname(String bgname) {
    this.bgname = bgname;
  }

  public Integer getSenceid() {
    return senceid;
  }

  public void setSenceid(Integer senceid) {
    this.senceid = senceid;
  }

  public Integer getMiddle() {
    return middle;
  }

  public void setMiddle(Integer middle) {
    this.middle = middle;
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
    HorseraceSenceType other = (HorseraceSenceType) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getBgname() == null ? other.getBgname() == null
            : this.getBgname().equals(other.getBgname()))
        && (this.getSenceid() == null ? other.getSenceid() == null
            : this.getSenceid().equals(other.getSenceid()))
        && (this.getMiddle() == null ? other.getMiddle() == null
            : this.getMiddle().equals(other.getMiddle()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getBgname() == null) ? 0 : getBgname().hashCode());
    result = prime * result + ((getSenceid() == null) ? 0 : getSenceid().hashCode());
    result = prime * result + ((getMiddle() == null) ? 0 : getMiddle().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", bgname=").append(bgname);
    sb.append(", senceid=").append(senceid);
    sb.append(", middle=").append(middle);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
