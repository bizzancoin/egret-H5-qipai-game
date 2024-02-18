package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class HorseraceLogPlan implements Serializable {
  /**
   * id
   */
  private Integer id;

  /**
   * 车标id
   */
  private Integer carid;

  /**
   * 车标方案typeid
   */
  private Integer type;

  /**
   * 车标名称
   */
  private String name;

  /**
   * 倍率
   */
  private Integer rate;

  /**
   * 出现几率‱
   */
  private Integer probability;

  private static final long serialVersionUID = 1L;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getCarid() {
    return carid;
  }

  public void setCarid(Integer carid) {
    this.carid = carid;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getRate() {
    return rate;
  }

  public void setRate(Integer rate) {
    this.rate = rate;
  }

  public Integer getProbability() {
    return probability;
  }

  public void setProbability(Integer probability) {
    this.probability = probability;
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
    HorseraceLogPlan other = (HorseraceLogPlan) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getCarid() == null ? other.getCarid() == null
            : this.getCarid().equals(other.getCarid()))
        && (this.getType() == null ? other.getType() == null
            : this.getType().equals(other.getType()))
        && (this.getName() == null ? other.getName() == null
            : this.getName().equals(other.getName()))
        && (this.getRate() == null ? other.getRate() == null
            : this.getRate().equals(other.getRate()))
        && (this.getProbability() == null ? other.getProbability() == null
            : this.getProbability().equals(other.getProbability()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getCarid() == null) ? 0 : getCarid().hashCode());
    result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
    result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
    result = prime * result + ((getRate() == null) ? 0 : getRate().hashCode());
    result = prime * result + ((getProbability() == null) ? 0 : getProbability().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", carid=").append(carid);
    sb.append(", type=").append(type);
    sb.append(", name=").append(name);
    sb.append(", rate=").append(rate);
    sb.append(", probability=").append(probability);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
