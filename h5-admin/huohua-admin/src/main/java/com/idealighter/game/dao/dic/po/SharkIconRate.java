package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class SharkIconRate implements Serializable {
  /**
   * id
   */
  private Integer id;

  /**
   * 图标id
   */
  private Integer icon;

  /**
   * 卡片方案plan的id
   */
  private Integer plan;

  /**
   * 卡片名称
   */
  private String name;

  /**
   * 最小倍率
   */
  private Integer minrate;

  /**
   * 最大倍率
   */
  private Integer maxrate;

  /**
   * 出现几率万分比
   */
  private Integer probability;

  private static final long serialVersionUID = 1L;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getIcon() {
    return icon;
  }

  public void setIcon(Integer icon) {
    this.icon = icon;
  }

  public Integer getPlan() {
    return plan;
  }

  public void setPlan(Integer plan) {
    this.plan = plan;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getMinrate() {
    return minrate;
  }

  public void setMinrate(Integer minrate) {
    this.minrate = minrate;
  }

  public Integer getMaxrate() {
    return maxrate;
  }

  public void setMaxrate(Integer maxrate) {
    this.maxrate = maxrate;
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
    SharkIconRate other = (SharkIconRate) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getIcon() == null ? other.getIcon() == null
            : this.getIcon().equals(other.getIcon()))
        && (this.getPlan() == null ? other.getPlan() == null
            : this.getPlan().equals(other.getPlan()))
        && (this.getName() == null ? other.getName() == null
            : this.getName().equals(other.getName()))
        && (this.getMinrate() == null ? other.getMinrate() == null
            : this.getMinrate().equals(other.getMinrate()))
        && (this.getMaxrate() == null ? other.getMaxrate() == null
            : this.getMaxrate().equals(other.getMaxrate()))
        && (this.getProbability() == null ? other.getProbability() == null
            : this.getProbability().equals(other.getProbability()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
    result = prime * result + ((getPlan() == null) ? 0 : getPlan().hashCode());
    result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
    result = prime * result + ((getMinrate() == null) ? 0 : getMinrate().hashCode());
    result = prime * result + ((getMaxrate() == null) ? 0 : getMaxrate().hashCode());
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
    sb.append(", icon=").append(icon);
    sb.append(", plan=").append(plan);
    sb.append(", name=").append(name);
    sb.append(", minrate=").append(minrate);
    sb.append(", maxrate=").append(maxrate);
    sb.append(", probability=").append(probability);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
