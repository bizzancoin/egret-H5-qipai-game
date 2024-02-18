package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class JcbyBattery implements Serializable {
  /**
   * 炮台id
   */
  private Integer id;

  /**
   * 炮管分数
   */
  private Integer score;

  /**
   * 炮管数量
   */
  private Integer num;

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

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
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
    JcbyBattery other = (JcbyBattery) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getScore() == null ? other.getScore() == null
            : this.getScore().equals(other.getScore()))
        && (this.getNum() == null ? other.getNum() == null : this.getNum().equals(other.getNum()))
        && (this.getDesc() == null ? other.getDesc() == null
            : this.getDesc().equals(other.getDesc()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
    result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());
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
    sb.append(", score=").append(score);
    sb.append(", num=").append(num);
    sb.append(", desc=").append(desc);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
