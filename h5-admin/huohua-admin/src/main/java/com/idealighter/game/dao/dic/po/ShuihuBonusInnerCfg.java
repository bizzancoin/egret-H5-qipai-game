package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class ShuihuBonusInnerCfg implements Serializable {
  /**
   * 编号
   */
  private Integer id;

  /**
   * 类型(1:3连线, 2: 4连线)
   */
  private Byte type;

  /**
   * 赔率
   */
  private Integer rate;

  /**
   * kickout概率分子
   */
  private Integer kickout;

  /**
   * kickOut分母
   */
  private Integer kickoutmax;

  private static final long serialVersionUID = 1L;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Byte getType() {
    return type;
  }

  public void setType(Byte type) {
    this.type = type;
  }

  public Integer getRate() {
    return rate;
  }

  public void setRate(Integer rate) {
    this.rate = rate;
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
    ShuihuBonusInnerCfg other = (ShuihuBonusInnerCfg) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getType() == null ? other.getType() == null
            : this.getType().equals(other.getType()))
        && (this.getRate() == null ? other.getRate() == null
            : this.getRate().equals(other.getRate()))
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
    result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
    result = prime * result + ((getRate() == null) ? 0 : getRate().hashCode());
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
    sb.append(", type=").append(type);
    sb.append(", rate=").append(rate);
    sb.append(", kickout=").append(kickout);
    sb.append(", kickoutmax=").append(kickoutmax);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
