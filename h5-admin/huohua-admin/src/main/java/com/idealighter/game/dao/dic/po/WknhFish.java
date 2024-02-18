package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class WknhFish implements Serializable {
  /**
   * 鱼id
   */
  private Integer id;

  /**
   * 包围盒id
   */
  private Integer bbxId;

  /**
   * 锁定等级
   */
  private Integer lockLeve;

  /**
   * 鱼类型(1:普通鱼,2:同类炸弹,3:全屏炸弹)
   */
  private Integer type;

  /**
   * 同类炸弹鱼种类
   */
  private String fishs;

  /**
   * 最小倍率
   */
  private Integer minRate;

  /**
   * 最大倍率
   */
  private Integer maxRate;

  /**
   * 鱼死亡后的刷鱼策略id
   */
  private Integer deathStrategy;

  /**
   * 公告类型(0:无公告,1:全服公告,2:本房间公告)
   */
  private Integer noticeType;

  /**
   * 公告内容
   */
  private String noticeContent;

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

  public Integer getBbxId() {
    return bbxId;
  }

  public void setBbxId(Integer bbxId) {
    this.bbxId = bbxId;
  }

  public Integer getLockLeve() {
    return lockLeve;
  }

  public void setLockLeve(Integer lockLeve) {
    this.lockLeve = lockLeve;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getFishs() {
    return fishs;
  }

  public void setFishs(String fishs) {
    this.fishs = fishs;
  }

  public Integer getMinRate() {
    return minRate;
  }

  public void setMinRate(Integer minRate) {
    this.minRate = minRate;
  }

  public Integer getMaxRate() {
    return maxRate;
  }

  public void setMaxRate(Integer maxRate) {
    this.maxRate = maxRate;
  }

  public Integer getDeathStrategy() {
    return deathStrategy;
  }

  public void setDeathStrategy(Integer deathStrategy) {
    this.deathStrategy = deathStrategy;
  }

  public Integer getNoticeType() {
    return noticeType;
  }

  public void setNoticeType(Integer noticeType) {
    this.noticeType = noticeType;
  }

  public String getNoticeContent() {
    return noticeContent;
  }

  public void setNoticeContent(String noticeContent) {
    this.noticeContent = noticeContent;
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
    WknhFish other = (WknhFish) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getBbxId() == null ? other.getBbxId() == null
            : this.getBbxId().equals(other.getBbxId()))
        && (this.getLockLeve() == null ? other.getLockLeve() == null
            : this.getLockLeve().equals(other.getLockLeve()))
        && (this.getType() == null ? other.getType() == null
            : this.getType().equals(other.getType()))
        && (this.getFishs() == null ? other.getFishs() == null
            : this.getFishs().equals(other.getFishs()))
        && (this.getMinRate() == null ? other.getMinRate() == null
            : this.getMinRate().equals(other.getMinRate()))
        && (this.getMaxRate() == null ? other.getMaxRate() == null
            : this.getMaxRate().equals(other.getMaxRate()))
        && (this.getDeathStrategy() == null ? other.getDeathStrategy() == null
            : this.getDeathStrategy().equals(other.getDeathStrategy()))
        && (this.getNoticeType() == null ? other.getNoticeType() == null
            : this.getNoticeType().equals(other.getNoticeType()))
        && (this.getNoticeContent() == null ? other.getNoticeContent() == null
            : this.getNoticeContent().equals(other.getNoticeContent()))
        && (this.getDesc() == null ? other.getDesc() == null
            : this.getDesc().equals(other.getDesc()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getBbxId() == null) ? 0 : getBbxId().hashCode());
    result = prime * result + ((getLockLeve() == null) ? 0 : getLockLeve().hashCode());
    result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
    result = prime * result + ((getFishs() == null) ? 0 : getFishs().hashCode());
    result = prime * result + ((getMinRate() == null) ? 0 : getMinRate().hashCode());
    result = prime * result + ((getMaxRate() == null) ? 0 : getMaxRate().hashCode());
    result = prime * result + ((getDeathStrategy() == null) ? 0 : getDeathStrategy().hashCode());
    result = prime * result + ((getNoticeType() == null) ? 0 : getNoticeType().hashCode());
    result = prime * result + ((getNoticeContent() == null) ? 0 : getNoticeContent().hashCode());
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
    sb.append(", bbxId=").append(bbxId);
    sb.append(", lockLeve=").append(lockLeve);
    sb.append(", type=").append(type);
    sb.append(", fishs=").append(fishs);
    sb.append(", minRate=").append(minRate);
    sb.append(", maxRate=").append(maxRate);
    sb.append(", deathStrategy=").append(deathStrategy);
    sb.append(", noticeType=").append(noticeType);
    sb.append(", noticeContent=").append(noticeContent);
    sb.append(", desc=").append(desc);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
