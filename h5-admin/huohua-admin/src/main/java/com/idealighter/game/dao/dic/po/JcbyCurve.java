package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class JcbyCurve implements Serializable {
  /**
   * 路径id
   */
  private Integer id;

  /**
   * 夹角
   */
  private Integer angle;

  /**
   * 缩放比例
   */
  private Integer ratio;

  /**
   * 路径配置数据
   */
  private String data;

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

  public Integer getAngle() {
    return angle;
  }

  public void setAngle(Integer angle) {
    this.angle = angle;
  }

  public Integer getRatio() {
    return ratio;
  }

  public void setRatio(Integer ratio) {
    this.ratio = ratio;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
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
    JcbyCurve other = (JcbyCurve) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getAngle() == null ? other.getAngle() == null
            : this.getAngle().equals(other.getAngle()))
        && (this.getRatio() == null ? other.getRatio() == null
            : this.getRatio().equals(other.getRatio()))
        && (this.getData() == null ? other.getData() == null
            : this.getData().equals(other.getData()))
        && (this.getDesc() == null ? other.getDesc() == null
            : this.getDesc().equals(other.getDesc()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getAngle() == null) ? 0 : getAngle().hashCode());
    result = prime * result + ((getRatio() == null) ? 0 : getRatio().hashCode());
    result = prime * result + ((getData() == null) ? 0 : getData().hashCode());
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
    sb.append(", angle=").append(angle);
    sb.append(", ratio=").append(ratio);
    sb.append(", data=").append(data);
    sb.append(", desc=").append(desc);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
