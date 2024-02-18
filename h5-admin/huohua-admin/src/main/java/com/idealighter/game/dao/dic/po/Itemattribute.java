package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class Itemattribute implements Serializable {
  /**
   * 道具ID
   */
  private Integer id;

  /**
   * 道具名（辨识用）
   */
  private String name;

  /**
   * 道具类型（道具在类型上的分组，0：会员，1：货币，2：礼品，3：喇叭，4：表情，）
   */
  private Integer type;

  /**
   * 道具使用后增加属性类型（0无，1元宝，2金币，3积分，4奖券）
   */
  private Integer get;

  /**
   * 增加属性数量
   */
  private Integer getnum;

  /**
   * 道具时限/天
   */
  private Integer time;

  /**
   * 是否允许对其他人使用（0允许，1不允许）
   */
  private Integer object;

  private static final long serialVersionUID = 1L;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Integer getGet() {
    return get;
  }

  public void setGet(Integer get) {
    this.get = get;
  }

  public Integer getGetnum() {
    return getnum;
  }

  public void setGetnum(Integer getnum) {
    this.getnum = getnum;
  }

  public Integer getTime() {
    return time;
  }

  public void setTime(Integer time) {
    this.time = time;
  }

  public Integer getObject() {
    return object;
  }

  public void setObject(Integer object) {
    this.object = object;
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
    Itemattribute other = (Itemattribute) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getName() == null ? other.getName() == null
            : this.getName().equals(other.getName()))
        && (this.getType() == null ? other.getType() == null
            : this.getType().equals(other.getType()))
        && (this.getGet() == null ? other.getGet() == null : this.getGet().equals(other.getGet()))
        && (this.getGetnum() == null ? other.getGetnum() == null
            : this.getGetnum().equals(other.getGetnum()))
        && (this.getTime() == null ? other.getTime() == null
            : this.getTime().equals(other.getTime()))
        && (this.getObject() == null ? other.getObject() == null
            : this.getObject().equals(other.getObject()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
    result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
    result = prime * result + ((getGet() == null) ? 0 : getGet().hashCode());
    result = prime * result + ((getGetnum() == null) ? 0 : getGetnum().hashCode());
    result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
    result = prime * result + ((getObject() == null) ? 0 : getObject().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", name=").append(name);
    sb.append(", type=").append(type);
    sb.append(", get=").append(get);
    sb.append(", getnum=").append(getnum);
    sb.append(", time=").append(time);
    sb.append(", object=").append(object);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
