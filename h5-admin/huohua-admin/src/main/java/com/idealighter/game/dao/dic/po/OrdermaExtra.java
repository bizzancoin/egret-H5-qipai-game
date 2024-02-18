package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class OrdermaExtra implements Serializable {
  /**
   * id
   */
  private Integer id;

  /**
   * 最小番
   */
  private Integer min;

  /**
   * 最大番
   */
  private Integer max;

  /**
   * 额外倍数
   */
  private Integer multiple;

  /**
   * 描述
   */
  private String desc;

  private static final long serialVersionUID = 1L;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getMin() {
    return min;
  }

  public void setMin(Integer min) {
    this.min = min;
  }

  public Integer getMax() {
    return max;
  }

  public void setMax(Integer max) {
    this.max = max;
  }

  public Integer getMultiple() {
    return multiple;
  }

  public void setMultiple(Integer multiple) {
    this.multiple = multiple;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
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
    OrdermaExtra other = (OrdermaExtra) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getMin() == null ? other.getMin() == null : this.getMin().equals(other.getMin()))
        && (this.getMax() == null ? other.getMax() == null : this.getMax().equals(other.getMax()))
        && (this.getMultiple() == null ? other.getMultiple() == null
            : this.getMultiple().equals(other.getMultiple()))
        && (this.getDesc() == null ? other.getDesc() == null
            : this.getDesc().equals(other.getDesc()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getMin() == null) ? 0 : getMin().hashCode());
    result = prime * result + ((getMax() == null) ? 0 : getMax().hashCode());
    result = prime * result + ((getMultiple() == null) ? 0 : getMultiple().hashCode());
    result = prime * result + ((getDesc() == null) ? 0 : getDesc().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", min=").append(min);
    sb.append(", max=").append(max);
    sb.append(", multiple=").append(multiple);
    sb.append(", desc=").append(desc);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
