package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class HorceraceLogType implements Serializable {
  /**
   * 方案id
   */
  private Integer planid;

  /**
   * 方案出现概率‱
   */
  private Integer planprobability;

  private static final long serialVersionUID = 1L;

  public Integer getPlanid() {
    return planid;
  }

  public void setPlanid(Integer planid) {
    this.planid = planid;
  }

  public Integer getPlanprobability() {
    return planprobability;
  }

  public void setPlanprobability(Integer planprobability) {
    this.planprobability = planprobability;
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
    HorceraceLogType other = (HorceraceLogType) that;
    return (this.getPlanid() == null ? other.getPlanid() == null
        : this.getPlanid().equals(other.getPlanid()))
        && (this.getPlanprobability() == null ? other.getPlanprobability() == null
            : this.getPlanprobability().equals(other.getPlanprobability()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getPlanid() == null) ? 0 : getPlanid().hashCode());
    result =
        prime * result + ((getPlanprobability() == null) ? 0 : getPlanprobability().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", planid=").append(planid);
    sb.append(", planprobability=").append(planprobability);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
