package com.idealighter.game.dao.data.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
public class RoomPrizePoolGroup extends RoomPrizePoolGroupKey implements Serializable {
  /**
   * 默认奖池
   */
  private String defaultPrizePool;

  /**
   * 创建时间
   */
  private Date createTime;

  /**
   * 更新时间
   */
  private Date updateTime;

  /**
   * 奖池,json格式
   */
  private String prizePools;

  private static final long serialVersionUID = 1L;

  public String getDefaultPrizePool() {
    return defaultPrizePool;
  }

  public void setDefaultPrizePool(String defaultPrizePool) {
    this.defaultPrizePool = defaultPrizePool;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public String getPrizePools() {
    return prizePools;
  }

  public void setPrizePools(String prizePools) {
    this.prizePools = prizePools;
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
    RoomPrizePoolGroup other = (RoomPrizePoolGroup) that;
    return (this.getGame() == null ? other.getGame() == null
        : this.getGame().equals(other.getGame()))
        && (this.getRoomId() == null ? other.getRoomId() == null
            : this.getRoomId().equals(other.getRoomId()))
        && (this.getDefaultPrizePool() == null ? other.getDefaultPrizePool() == null
            : this.getDefaultPrizePool().equals(other.getDefaultPrizePool()))
        && (this.getCreateTime() == null ? other.getCreateTime() == null
            : this.getCreateTime().equals(other.getCreateTime()))
        && (this.getUpdateTime() == null ? other.getUpdateTime() == null
            : this.getUpdateTime().equals(other.getUpdateTime()))
        && (this.getPrizePools() == null ? other.getPrizePools() == null
            : this.getPrizePools().equals(other.getPrizePools()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getGame() == null) ? 0 : getGame().hashCode());
    result = prime * result + ((getRoomId() == null) ? 0 : getRoomId().hashCode());
    result =
        prime * result + ((getDefaultPrizePool() == null) ? 0 : getDefaultPrizePool().hashCode());
    result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
    result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
    result = prime * result + ((getPrizePools() == null) ? 0 : getPrizePools().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", defaultPrizePool=").append(defaultPrizePool);
    sb.append(", createTime=").append(createTime);
    sb.append(", updateTime=").append(updateTime);
    sb.append(", prizePools=").append(prizePools);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
