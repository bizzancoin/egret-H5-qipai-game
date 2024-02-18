package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class ShuihuBonusOuterKickOut implements Serializable {
  /**
   * 编号
   */
  private Integer id;

  /**
   * 图标编号
   */
  private Integer iconid;

  /**
   * kickout概率分子
   */
  private Integer kickout;

  private Integer kickoutmax;

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

  public Integer getKickout() {
    return kickout;
  }

  public void setKickout(Integer kickout) {
    this.kickout = kickout;
  }

  public Integer getKickoutmax() {
    return kickoutmax;
  }

  public void setKickoutmax(Integer kickoutmax) {
    this.kickoutmax = kickoutmax;
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
    ShuihuBonusOuterKickOut other = (ShuihuBonusOuterKickOut) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getIconid() == null ? other.getIconid() == null
            : this.getIconid().equals(other.getIconid()))
        && (this.getKickout() == null ? other.getKickout() == null
            : this.getKickout().equals(other.getKickout()))
        && (this.getKickoutmax() == null ? other.getKickoutmax() == null
            : this.getKickoutmax().equals(other.getKickoutmax()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getIconid() == null) ? 0 : getIconid().hashCode());
    result = prime * result + ((getKickout() == null) ? 0 : getKickout().hashCode());
    result = prime * result + ((getKickoutmax() == null) ? 0 : getKickoutmax().hashCode());
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
    sb.append(", kickout=").append(kickout);
    sb.append(", kickoutmax=").append(kickoutmax);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
