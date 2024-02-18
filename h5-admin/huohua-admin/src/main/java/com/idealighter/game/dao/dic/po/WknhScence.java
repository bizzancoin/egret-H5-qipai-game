package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class WknhScence implements Serializable {
  /**
   * 场景id
   */
  private Integer id;

  /**
   * 刷鱼策略
   */
  private String strategys;

  /**
   * 场景存在时间（秒）
   */
  private Integer lifetime;

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

  public String getStrategys() {
    return strategys;
  }

  public void setStrategys(String strategys) {
    this.strategys = strategys;
  }

  public Integer getLifetime() {
    return lifetime;
  }

  public void setLifetime(Integer lifetime) {
    this.lifetime = lifetime;
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
    WknhScence other = (WknhScence) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getStrategys() == null ? other.getStrategys() == null
            : this.getStrategys().equals(other.getStrategys()))
        && (this.getLifetime() == null ? other.getLifetime() == null
            : this.getLifetime().equals(other.getLifetime()))
        && (this.getDesc() == null ? other.getDesc() == null
            : this.getDesc().equals(other.getDesc()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getStrategys() == null) ? 0 : getStrategys().hashCode());
    result = prime * result + ((getLifetime() == null) ? 0 : getLifetime().hashCode());
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
    sb.append(", strategys=").append(strategys);
    sb.append(", lifetime=").append(lifetime);
    sb.append(", desc=").append(desc);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
