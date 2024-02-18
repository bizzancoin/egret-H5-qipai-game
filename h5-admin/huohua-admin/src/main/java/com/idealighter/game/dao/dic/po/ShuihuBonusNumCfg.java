package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class ShuihuBonusNumCfg implements Serializable {
  /**
   * 编号
   */
  private Integer id;

  /**
   * 水浒传数量(3-5:为单线, 45位满屏)
   */
  private Byte shuihuNum;

  /**
   * bonus game次数
   */
  private Byte bonusNum;

  private static final long serialVersionUID = 1L;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Byte getShuihuNum() {
    return shuihuNum;
  }

  public void setShuihuNum(Byte shuihuNum) {
    this.shuihuNum = shuihuNum;
  }

  public Byte getBonusNum() {
    return bonusNum;
  }

  public void setBonusNum(Byte bonusNum) {
    this.bonusNum = bonusNum;
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
    ShuihuBonusNumCfg other = (ShuihuBonusNumCfg) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getShuihuNum() == null ? other.getShuihuNum() == null
            : this.getShuihuNum().equals(other.getShuihuNum()))
        && (this.getBonusNum() == null ? other.getBonusNum() == null
            : this.getBonusNum().equals(other.getBonusNum()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getShuihuNum() == null) ? 0 : getShuihuNum().hashCode());
    result = prime * result + ((getBonusNum() == null) ? 0 : getBonusNum().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", shuihuNum=").append(shuihuNum);
    sb.append(", bonusNum=").append(bonusNum);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
